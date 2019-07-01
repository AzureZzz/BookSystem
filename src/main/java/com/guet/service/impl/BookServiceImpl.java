package com.guet.service.impl;

import com.guet.dao.BookInfoMapper;
import com.guet.dao.BookMapper;
import com.guet.entity.Book;
import com.guet.service.IBookService;
import com.guet.utils.ReturnMessage;
import com.guet.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public void addBook(Book book) throws Exception {
        bookMapper.insert(book);
    }

    @Override
    public Book getBook(String bookNo) throws Exception {
        Book book = bookMapper.selectByPrimaryKey(bookNo);
        if(book!=null)
        {
            book.setBookInfo(bookInfoMapper.selectByPrimaryKey(book.getCallNum()));
        }
        return book;
    }

    @Override
    public void deleteBook(String bookNo) throws Exception {
        bookMapper.deleteByPrimaryKey(bookNo);
    }

    @Override
    public void updateBook(Book book) throws Exception {
        bookMapper.updateByPrimaryKeySelective(book);
    }

    @Override
    public Map<String, Object> getAllBooks(String aoData) throws Exception {
        Map<String,Object> params = ServiceUtils.getAoDataParams(aoData);
        String sEcho = (String) params.get("sEcho");
        int iDisplayStart = (int) params.get("iDisplayStart");
        int iDisplayLength = (int) params.get("iDisplayLength");
        String column = "book_no";
        boolean sortDir = (boolean) params.get("sortDir");
        String keyWord = (String) params.get("keyWord");

        int totalCount = bookMapper.selectAllCount();
        List<Book> books =  bookMapper.selectAllBooks(iDisplayStart,iDisplayLength,sortDir,column,keyWord);
        int displayCount = books.size();
        return ReturnMessage.dataTablesResult(sEcho,totalCount,displayCount,books);
    }

    @Override
    public Map<String, Object> getBooks(String callNum, String aoData) throws Exception {
        Map<String,Object> params = ServiceUtils.getAoDataParams(aoData);
        String sEcho = (String) params.get("sEcho");
        int iDisplayStart = (int) params.get("iDisplayStart");
        int iDisplayLength = (int) params.get("iDisplayLength");
        String column = "book_no";
        boolean sortDir = (boolean) params.get("sortDir");
        String keyWord = (String) params.get("keyWord");

        System.out.println(callNum);
        int totalCount = bookMapper.getCountBook(callNum);
        List<Book> books =  bookMapper.selectBooks(callNum,iDisplayStart,iDisplayLength,sortDir,column,keyWord);
        int displayCount = books.size();
        return ReturnMessage.dataTablesResult(sEcho,totalCount,displayCount,books);
    }
}
