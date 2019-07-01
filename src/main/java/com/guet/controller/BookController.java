package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.Book;
import com.guet.service.IBookService;
import com.guet.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private IBookService bookService;

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "book",method = RequestMethod.POST)
    public Map<String,Object> newBook(@RequestBody Book book) {
        try {
            book.setState((byte)0);
            bookService.addBook(book);
            return ReturnMessage.getResult(0,"添加成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"添加失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "book/{bookNo}",method = RequestMethod.GET)
    public Map<String,Object> getBook(@PathVariable("bookNo") String bookNo) {
        try {
            Book book = bookService.getBook(bookNo);
            return ReturnMessage.getResult(0,"获取成功！",book);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"获取失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "book/{bookNo}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteBook(@PathVariable("bookNo") String bookNo) {
        try {
            bookService.deleteBook(bookNo);
            return ReturnMessage.getResult(0,"删除成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"删除失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "book",method = RequestMethod.PUT)
    public Map<String,Object> updateBook(@RequestBody Book book) {
        try {
            bookService.updateBook(book);
            return ReturnMessage.getResult(0,"修改成功！",book);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"修改失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "/bookInfos",method = RequestMethod.GET)
    public Map<String,Object> getBookInfos(@RequestParam String aoData) {
        try {
            return bookService.getAllBooks(aoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "/bookInfos/{callNum}",method = RequestMethod.GET)
    public Map<String,Object> getBookInfos(@RequestParam String aoData,
                                           @PathVariable String callNum) {
        try {
            return bookService.getBooks(callNum,aoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
