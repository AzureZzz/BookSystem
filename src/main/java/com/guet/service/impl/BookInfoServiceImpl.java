package com.guet.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guet.dao.BookInfoMapper;
import com.guet.dao.BookMapper;
import com.guet.dao.BorrowMapper;
import com.guet.dao.CollectMapper;
import com.guet.entity.Book;
import com.guet.entity.BookExcelRow;
import com.guet.entity.BookInfo;
import com.guet.entity.BookInfoRow;
import com.guet.service.IBookInfoService;
import com.guet.utils.ReturnMessage;
import com.guet.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BookInfoServiceImpl implements IBookInfoService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public void addBookInfo(BookInfo bookInfo) throws Exception {
        bookInfoMapper.insert(bookInfo);
    }

    @Override
    public void updateBookInfo(BookInfo bookInfo) throws Exception {
        bookMapper.deleteByCallNum(bookInfo.getCallNum());
        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
    }

    @Override
    public BookInfo getBookInfo(String callNum) throws Exception {
        return bookInfoMapper.selectByPrimaryKey(callNum);
    }

    @Override
    public void deleteBookInfo(String callNum) throws Exception {
        bookMapper.deleteByCallNum(callNum);
        bookInfoMapper.deleteByPrimaryKey(callNum);
    }

    @Override
    public Map<String ,Object> getBookInfos(String aoData) throws Exception {
        String[] cols = {"call_num","call_num","name","cover","writer","press"};
        Map<String,Object> params = ServiceUtils.getAoDataParams(aoData);
        String sEcho = (String) params.get("sEcho");
        int iDisplayStart = (int) params.get("iDisplayStart");
        int iDisplayLength = (int) params.get("iDisplayLength");
        String column = cols[(int) params.get("columnIndex")];
        boolean sortDir = (boolean) params.get("sortDir");
        String keyWord = (String) params.get("keyWord");

        System.out.println(column);

        int totalCount = bookInfoMapper.selectAllCount();
        List<BookInfo> bookInfos =  bookInfoMapper.selectBookInfos(iDisplayStart,iDisplayLength,sortDir,column,keyWord);
        int displayCount = bookInfos.size();
        List<BookInfoRow> rows = new ArrayList<>();
        Iterator<BookInfo> it = bookInfos.iterator();
        BookInfo bookInfo = null;
        while (it.hasNext()){
            bookInfo = it.next();
            BookInfoRow row = new BookInfoRow();
            row.setCallNum(bookInfo.getCallNum());
            row.setName(bookInfo.getName());
            row.setPress(bookInfo.getPress());
            row.setWriter(bookInfo.getWriter());
            row.setCover(bookInfo.getCover());
            row.setTotal(bookMapper.getCountBook(bookInfo.getCallNum()));
            row.setRemain(bookMapper.getCountBookState(bookInfo.getCallNum(),(byte)0));
            rows.add(row);
        }
        return ReturnMessage.dataTablesResult(sEcho,totalCount,displayCount,rows);
    }

    @Override
    public List<BookExcelRow> getExcelBooks() throws Exception {
        List<BookInfo> bookInfos = bookInfoMapper.selectAll();
        List<BookExcelRow> rows = new ArrayList<>();
        Iterator<BookInfo> it = bookInfos.iterator();
        BookInfo bookInfo = null;
        while (it.hasNext()){
            bookInfo = it.next();
            BookExcelRow row = new BookExcelRow();
            row.setCallNum(bookInfo.getCallNum());
            row.setName(bookInfo.getName());
            row.setPress(bookInfo.getPress());
            row.setWriter(bookInfo.getWriter());
            row.setCover(bookInfo.getCover());
            row.setIsbn(bookInfo.getIsbn());
            row.setDigest(bookInfo.getDigest());
            row.setPageNum(bookInfo.getPageNum());
            row.setPubTime(bookInfo.getPubTime());
            row.setPiece(bookInfo.getPiece());
            row.setBorrowNum(borrowMapper.selectBookCount(bookInfo.getCallNum()));
            row.setCollectNum(collectMapper.selectCountBook(bookInfo.getCallNum()));
            row.setTotal(bookMapper.getCountBook(bookInfo.getCallNum()));
            row.setRemain(bookMapper.getCountBookState(bookInfo.getCallNum(),(byte)0));
            rows.add(row);
        }
        return rows;
    }
}
