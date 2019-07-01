package com.guet.service.impl;

import com.guet.dao.BookMapper;
import com.guet.dao.BorrowMapper;
import com.guet.dao.ReaderMapper;
import com.guet.entity.Reader;
import com.guet.entity.ReaderRow;
import com.guet.service.IReaderService;
import com.guet.utils.ReturnMessage;
import com.guet.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReaderServiceImpl implements IReaderService {

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Reader login(String username, String password) throws Exception {
        Reader reader = readerMapper.selectLogin(username,ServiceUtils.md5(password));
        reader.setPassword("");
        return reader;
    }

    @Override
    public Reader getReader(String username) throws Exception {
        Reader reader = readerMapper.selectByPrimaryKey(username);
        if(reader !=null){
            reader.setPassword("");
        }
        return reader;
    }

    @Override
    public boolean updatePassword(String username, String newPassword) throws Exception {
        Reader reader = new Reader();
        reader.setPassword(ServiceUtils.md5(newPassword));
        reader.setUsername(username);
        readerMapper.updateByPrimaryKeySelective(reader);
        return true;
    }

    @Override
    public boolean updatePassword(String username, String oldPassword, String newPassword) throws Exception {
        Reader reader = readerMapper.selectByPrimaryKey(username);
        if(ServiceUtils.md5(oldPassword.trim()).equals(reader.getPassword())){
            reader.setPassword(ServiceUtils.md5(newPassword));
            readerMapper.updateByPrimaryKeySelective(reader);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void updateInfo(Reader reader) throws Exception {
        readerMapper.updateByPrimaryKeySelective(reader);
    }

    @Override
    public void register(Reader reader) throws Exception {
        reader.setPassword(ServiceUtils.md5(reader.getPassword()));
        reader.setSignTime(new Date());
        readerMapper.insert(reader);
    }

    @Override
    public void deleteReader(String username) throws Exception {
        bookMapper.resetState(username);
        readerMapper.deleteByPrimaryKey(username);
    }

    @Override
    public Map<String, Object> getReaders(String aoData) throws Exception {
        String[] cols = {"username","username","name","type","email","balance"};
        Map<String,Object> params = ServiceUtils.getAoDataParams(aoData);
        String sEcho = (String) params.get("sEcho");
        int iDisplayStart = (int) params.get("iDisplayStart");
        int iDisplayLength = (int) params.get("iDisplayLength");
        String column = cols[(int) params.get("columnIndex")];
        boolean sortDir = (boolean) params.get("sortDir");
        String keyWord = (String) params.get("keyWord");

        int totalCount = readerMapper.selectAllCount();
        List<Reader> readers =  readerMapper.selectReaders(iDisplayStart,iDisplayLength,sortDir,column,keyWord);
        int displayCount = readers.size();
        List<ReaderRow> rows = new ArrayList<>();
        Iterator<Reader> it = readers.iterator();
        Reader reader = null;
        while (it.hasNext()){
            reader = it.next();
            ReaderRow row = new ReaderRow();
            row.setBalance(reader.getBalance());
            row.setEmail(reader.getEmail());
            row.setName(reader.getName());
            row.setType(reader.getType());
            row.setUsername(reader.getUsername());
            row.setBorrowNum(borrowMapper.selectAllCountReader(reader.getUsername()));
            row.setUnReturn(borrowMapper.selectReaderCountState((byte)0,reader.getUsername()));
            row.setOverNum(borrowMapper.selectReaderUnReturnCount(reader.getUsername()));
            rows.add(row);
        }
        return ReturnMessage.dataTablesResult(sEcho,totalCount,displayCount,rows);
    }

    @Override
    public int getReaderCount(int type) throws Exception {
        Date date = new Date();
        switch (type){
            case 0:return readerMapper.selectAllCount();
            case 1:return readerMapper.selectCountDate(date,ServiceUtils.addAndSubtractDaysByCalendar(date,1));
            case 2:return readerMapper.selectCountDate(ServiceUtils.addAndSubtractDaysByCalendar(date,-1),date);
            case 3:return readerMapper.selectCountDate(ServiceUtils.addAndSubtractDaysByCalendar(date,-7),date);
            case 4:return readerMapper.selectCountDate(ServiceUtils.addAndSubtractDaysByCalendar(date,-30),date);
        }
        return 0;
    }

    @Override
    public List<ReaderRow> getExcelReaders() throws Exception {
        List<Reader> readers = readerMapper.selectAll();
        List<ReaderRow> rows = new ArrayList<>();
        Iterator<Reader> it = readers.iterator();
        Reader reader = null;
        while (it.hasNext()){
            reader = it.next();
            ReaderRow row = new ReaderRow();
            row.setBalance(reader.getBalance());
            row.setEmail(reader.getEmail());
            row.setName(reader.getName());
            row.setType(reader.getType());
            row.setUsername(reader.getUsername());
            row.setBorrowNum(borrowMapper.selectAllCountReader(reader.getUsername()));
            row.setUnReturn(borrowMapper.selectReaderCountState((byte)0,reader.getUsername()));
            row.setOverNum(borrowMapper.selectReaderUnReturnCount(reader.getUsername()));
            rows.add(row);
        }
        return rows;
    }

    @Override
    public List<Integer> getWeekCount() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int week = ServiceUtils.dayForWeek(df.format(new Date()));
        Date date = ServiceUtils.addAndSubtractDaysByCalendar(new Date(),1-week);
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<7;i++){
            result.add(readerMapper.selectCountDate(date,ServiceUtils.addAndSubtractDaysByCalendar(date,1)));
            date = ServiceUtils.addAndSubtractDaysByCalendar(date,1);
        }
        System.out.println(result);
        return result;
    }
}
