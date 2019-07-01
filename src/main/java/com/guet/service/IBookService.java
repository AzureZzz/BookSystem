package com.guet.service;

import com.guet.entity.Book;

import java.util.List;
import java.util.Map;

public interface IBookService {

    void addBook(Book book)throws Exception;

    Book getBook(String bookNo) throws Exception;

    void deleteBook(String bookNo) throws Exception;

    void updateBook(Book book) throws Exception;

    Map<String,Object> getAllBooks(String aoData)throws Exception;

    Map<String,Object> getBooks(String callNum,String aoData)throws Exception;

}
