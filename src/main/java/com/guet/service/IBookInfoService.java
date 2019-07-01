package com.guet.service;

import com.guet.entity.BookExcelRow;
import com.guet.entity.BookInfo;
import com.guet.entity.BookInfoRow;

import java.util.List;
import java.util.Map;

public interface IBookInfoService {

    void addBookInfo(BookInfo bookInfo)throws Exception;

    void updateBookInfo(BookInfo bookInfo)throws Exception;

    BookInfo getBookInfo(String callNum)throws Exception;

    void deleteBookInfo(String callNum) throws Exception;

    Map<String,Object> getBookInfos(String aoData)throws Exception;

    List<BookExcelRow> getExcelBooks()throws Exception;
}
