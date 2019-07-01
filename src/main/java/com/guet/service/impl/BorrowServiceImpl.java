package com.guet.service.impl;

import com.guet.dao.BookMapper;
import com.guet.dao.BorrowMapper;
import com.guet.entity.Book;
import com.guet.entity.Borrow;
import com.guet.entity.BorrowKey;
import com.guet.service.IBorrowService;
import com.guet.utils.ReturnMessage;
import com.guet.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BorrowServiceImpl implements IBorrowService {

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void addBorrow(Borrow borrow) throws Exception {
        Book book = new Book();
        book.setBookNo(borrow.getBookNo());
        book.setState((byte)1);
        bookMapper.updateByPrimaryKeySelective(book);
        borrowMapper.insert(borrow);
    }

    @Override
    public Borrow getBorrow(BorrowKey borrowKey) throws Exception {
        return borrowMapper.selectByPrimaryKey(borrowKey);
    }

    @Override
    public void deleteBorrow(BorrowKey borrowKey) throws Exception {
        Book book = new Book();
        book.setBookNo(borrowKey.getBookNo());
        book.setState((byte)0);
        bookMapper.updateByPrimaryKeySelective(book);
        borrowMapper.deleteByPrimaryKey(borrowKey);
    }

    @Override
    public void updateBorrow(Borrow borrow) throws Exception {
        borrowMapper.updateByPrimaryKeySelective(borrow);
    }

    @Override
    public boolean renew(BorrowKey borrowKey) throws Exception {
        Borrow borrow = borrowMapper.selectByPrimaryKey(borrowKey);
        if(borrow.getReNew()>=3) {
            return false;
        }else{
            borrow.setReNew((byte)(borrow.getReNew()+1));
            borrow.setRetTime(ServiceUtils.addAndSubtractDaysByCalendar(borrow.getRetTime(),30));
            borrowMapper.updateByPrimaryKeySelective(borrow);
            return true;
        }
    }

    @Override
    public Map<String, Object> getBorrows(String aoData) throws Exception {
        String[] cols = {"book_no","book_no","username","bor_time","ret_time","renew","state"};
        Map<String,Object> params = ServiceUtils.getAoDataParams(aoData);
        String sEcho = (String) params.get("sEcho");
        int iDisplayStart = (int) params.get("iDisplayStart");
        int iDisplayLength = (int) params.get("iDisplayLength");
        String column = cols[(int) params.get("columnIndex")];
        boolean sortDir = (boolean) params.get("sortDir");
        String keyWord = (String) params.get("keyWord");

        int totalCount = borrowMapper.selectAllCount();
        List<Borrow> borrows =  borrowMapper.selectBorrows(iDisplayStart,iDisplayLength,sortDir,column,keyWord);
        int displayCount = borrows.size();
        return ReturnMessage.dataTablesResult(sEcho,totalCount,displayCount,borrows);
    }

    @Override
    public Map<String, Object> getReaderBorrows(String username, String aoData) throws Exception {
        String[] cols = {"book_no","book_no","username","bor_time","ret_time","renew","state"};
        Map<String,Object> params = ServiceUtils.getAoDataParams(aoData);
        String sEcho = (String) params.get("sEcho");
        int iDisplayStart = (int) params.get("iDisplayStart");
        int iDisplayLength = (int) params.get("iDisplayLength");
        String column = cols[(int) params.get("columnIndex")];
        boolean sortDir = (boolean) params.get("sortDir");
        String keyWord = (String) params.get("keyWord");

        int totalCount = borrowMapper.selectAllCountReader(username);
        List<Borrow> borrows =  borrowMapper.selectReaderBorrows(username,iDisplayStart,iDisplayLength,sortDir,column,keyWord);
        int displayCount = borrows.size();
        return ReturnMessage.dataTablesResult(sEcho,totalCount,displayCount,borrows);
    }

    @Override
    public int getAllCount(Byte state) throws Exception {
        return borrowMapper.selectCountState(state);
    }

    @Override
    public int getDateCount(int dateType, Byte state) throws Exception {
        Date date=new Date();
        switch (dateType){
            case 0:return borrowMapper.selectCountState(state);
            case 1:return borrowMapper.selectCountStateDate(state,date,ServiceUtils.addAndSubtractDaysByCalendar(date,1));
            case 2:return borrowMapper.selectCountStateDate(state,ServiceUtils.addAndSubtractDaysByCalendar(date,-1),date);
            case 3:return borrowMapper.selectCountStateDate(state,ServiceUtils.addAndSubtractDaysByCalendar(date,-7),date);
            case 4:return borrowMapper.selectCountStateDate(state,ServiceUtils.addAndSubtractDaysByCalendar(date,-30),date);
        }
        return 0;
    }

    @Override
    public int getReaderCount(String username, Byte state) throws Exception {
        return borrowMapper.selectCountStateReader(username,state);
    }

    @Override
    public List<String> getTenReader() throws Exception {
        return null;
    }

    @Override
    public List<String> getTenBook() throws Exception {
        return null;
    }

    @Override
    public List<Borrow> getExcelBorrows() throws Exception {
        return borrowMapper.selectAll();
    }

    @Override
    public void returnBook(BorrowKey borrowKey) throws Exception {
        Borrow borrow = borrowMapper.selectByPrimaryKey(borrowKey);
        borrow.setState((byte)1);
        borrow.setRetTime(new Date());
        borrowMapper.updateByPrimaryKeySelective(borrow);
    }

    @Override
    public List<Integer> getBorrowWeekCount() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int week = ServiceUtils.dayForWeek(df.format(new Date()));
        Date date = ServiceUtils.addAndSubtractDaysByCalendar(new Date(),1-week);
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<7;i++){
            result.add(borrowMapper.selectCountStateDate((byte) 0,date,ServiceUtils.addAndSubtractDaysByCalendar(date,1)));
            date = ServiceUtils.addAndSubtractDaysByCalendar(date,1);
        }
        System.out.println(result);
        return result;
    }

    @Override
    public List<Integer> getReturnWeekCount() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int week = ServiceUtils.dayForWeek(df.format(new Date()));
        Date date = ServiceUtils.addAndSubtractDaysByCalendar(new Date(),1-week);
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<7;i++){
            result.add(borrowMapper.selectCountStateDate((byte) 1,date,ServiceUtils.addAndSubtractDaysByCalendar(date,1)));
            System.out.println(date);
            date = ServiceUtils.addAndSubtractDaysByCalendar(date,1);
            System.out.println(date);
        }
        System.out.println(result);
        return result;
    }
}
