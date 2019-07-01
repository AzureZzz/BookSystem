package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.*;
import com.guet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "admin")
public class AdminPageController {

    @Autowired
    private IReserveService reserveService;

    @Autowired
    private IReaderService readerService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBookInfoService bookInfoService;

    @Autowired
    private IBorrowService borrowService;

    @Autowired
    private IAdminService adminService;

    @AuthAdmin
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView toIndex(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("index");
        String admin = (String) request.getSession().getAttribute("admin");
        try {
            Admin a = adminService.getAdmin(admin);
            modelAndView.addObject("admin",a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/bookAdd",method = RequestMethod.GET)
    public ModelAndView toBookAdd(){
        ModelAndView modelAndView = new ModelAndView("book-add");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/bookEdit/{callNum}",method = RequestMethod.GET)
    public ModelAndView toBookEdit(@PathVariable("callNum")String callNum){
        ModelAndView modelAndView = new ModelAndView("book-edit");
        try {
            BookInfo bookInfo = bookInfoService.getBookInfo(callNum);
            modelAndView.addObject("bookInfo",bookInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/bookInfoShow/{callNum}",method = RequestMethod.GET)
    public ModelAndView toBookShow(@PathVariable("callNum")String callNum){
        ModelAndView modelAndView = new ModelAndView("book-info");
        try {
            BookInfo bookInfo = bookInfoService.getBookInfo(callNum);
            modelAndView.addObject("bookInfo",bookInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/bookList",method = RequestMethod.GET)
    public ModelAndView toBookList(){
        ModelAndView modelAndView = new ModelAndView("book-list");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/bookNumAdd/{callNum}",method = RequestMethod.GET)
    public ModelAndView toBookNumAdd(@PathVariable("callNum")String callNum){
        ModelAndView modelAndView = new ModelAndView("book-num-add");
        modelAndView.addObject("callNum",callNum);
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/bookNumEdit/{bookNo}",method = RequestMethod.GET)
    public ModelAndView toBookNumEdit(@PathVariable("bookNo")String bookNo){
        ModelAndView modelAndView = new ModelAndView("book-num-edit");
        try {
            Book book = bookService.getBook(bookNo);
            modelAndView.addObject("book",book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/bookNumList/{callNum}",method = RequestMethod.GET)
    public ModelAndView toBookNumList(@PathVariable("callNum")String callNum){
        ModelAndView modelAndView = new ModelAndView("book-num-list");
        modelAndView.addObject("callNum",callNum);
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/bookShow/{bookNo}",method = RequestMethod.GET)
    public ModelAndView toBookInfo(@PathVariable("bookNo")String bookNo){
        ModelAndView modelAndView = new ModelAndView("book-show");
        try {
            Book book = bookService.getBook(bookNo);
            modelAndView.addObject("book",book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/borrowAdd",method = RequestMethod.GET)
    public ModelAndView toBorrowAdd(){
        ModelAndView modelAndView = new ModelAndView("borrow-add");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/borrowEdit/{username}/{bookNo}/{borTime}",method = RequestMethod.GET)
    public ModelAndView toBorrowEdit(@PathVariable("username")String username,
                                     @PathVariable("bookNo") String bookNo,
                                     @PathVariable("borTime")String borTime){
        BorrowKey borrowKey = new BorrowKey();
        borrowKey.setBookNo(bookNo);
        borrowKey.setUsername(username);
        borrowKey.setBorTime(new Date(Long.valueOf(borTime)));
        ModelAndView modelAndView = new ModelAndView("borrow-edit");
        try {
            Borrow borrow = borrowService.getBorrow(borrowKey);
            modelAndView.addObject("borrow",borrow);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/borrowLine",method = RequestMethod.GET)
    public ModelAndView toBorrowLine(){
        ModelAndView modelAndView = new ModelAndView("borrow-line");
        try {
            modelAndView.addObject("reader",readerService.getWeekCount());
            modelAndView.addObject("borrow",borrowService.getBorrowWeekCount());
            modelAndView.addObject("ret",borrowService.getReturnWeekCount());
            modelAndView.addObject("reserve",reserveService.getWeekCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/borrowList",method = RequestMethod.GET)
    public ModelAndView toBorrowList(){
        ModelAndView modelAndView = new ModelAndView("borrow-list");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/changePassword/{username}",method = RequestMethod.GET)
    public ModelAndView toChangePassword(@PathVariable("username")String username){
        ModelAndView modelAndView = new ModelAndView("change-password");
        modelAndView.addObject("username",username);
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/hotBook",method = RequestMethod.GET)
    public ModelAndView toHotBook(){
        ModelAndView modelAndView = new ModelAndView("hot-book");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/hotKind",method = RequestMethod.GET)
    public ModelAndView toHotKind(){
        ModelAndView modelAndView = new ModelAndView("hot-kind");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/hotReader",method = RequestMethod.GET)
    public ModelAndView toHotReader(){
        ModelAndView modelAndView = new ModelAndView("hot-reader");
        return modelAndView;
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView toLogin(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/overList",method = RequestMethod.GET)
    public ModelAndView toOverList(){
        ModelAndView modelAndView = new ModelAndView("over-list");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/readerAdd",method = RequestMethod.GET)
    public ModelAndView toReaderAdd(){
        ModelAndView modelAndView = new ModelAndView("reader-add");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/readerEdit/{username}",method = RequestMethod.GET)
    public ModelAndView toReaderEdit(@PathVariable("username")String username){
        ModelAndView modelAndView = new ModelAndView("reader-edit");
        try {
            Reader reader = readerService.getReader(username);
            modelAndView.addObject("reader",reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/readerList",method = RequestMethod.GET)
    public ModelAndView toReaderList(){
        ModelAndView modelAndView = new ModelAndView("reader-list");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/readerShow/{username}",method = RequestMethod.GET)
    public ModelAndView toReaderShow(@PathVariable("username")String username){
        ModelAndView modelAndView = new ModelAndView("reader-show");
        try {
            Reader reader = readerService.getReader(username);
            modelAndView.addObject("reader",reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/reserveAdd",method = RequestMethod.GET)
    public ModelAndView toReserveAdd(){
        ModelAndView modelAndView = new ModelAndView("reserve-add");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/reserveEdit/{username}/{bookNo}",method = RequestMethod.GET)
    public ModelAndView toReserveEdit(@PathVariable("username")String username,
                                      @PathVariable("bookNo")String bookNo){
        ModelAndView modelAndView = new ModelAndView("reserve-edit");
        ReserveKey reserveKey = new ReserveKey();
        reserveKey.setUsername(username);
        reserveKey.setBookNo(bookNo);
        try {
            Reserve reserve = reserveService.getReserve(reserveKey);
            modelAndView.addObject("reserve",reserve);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/reserveList",method = RequestMethod.GET)
    public ModelAndView toReserveList(){
        ModelAndView modelAndView = new ModelAndView("reserve-list");
        return modelAndView;
    }

    @AuthAdmin
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public ModelAndView toWelcome(){
        ModelAndView modelAndView = new ModelAndView("welcome");
        try {
            modelAndView.addObject("readerTotal",readerService.getReaderCount(0));
            modelAndView.addObject("readerToday",readerService.getReaderCount(1));
            modelAndView.addObject("readerYesterday",readerService.getReaderCount(2));
            modelAndView.addObject("readerWeek",readerService.getReaderCount(3));
            modelAndView.addObject("readerMonth",readerService.getReaderCount(4));

            modelAndView.addObject("borrowTotal",borrowService.getDateCount(0,(byte)0));
            modelAndView.addObject("borrowToday",borrowService.getDateCount(1,(byte)0));
            modelAndView.addObject("borrowYesterday",borrowService.getDateCount(2,(byte)0));
            modelAndView.addObject("borrowWeek",borrowService.getDateCount(3,(byte)0));
            modelAndView.addObject("borrowMonth",borrowService.getDateCount(4,(byte)0));

            modelAndView.addObject("returnTotal",borrowService.getDateCount(0,(byte)1));
            modelAndView.addObject("returnToday",borrowService.getDateCount(1,(byte)1));
            modelAndView.addObject("returnYesterday",borrowService.getDateCount(2,(byte)1));
            modelAndView.addObject("returnWeek",borrowService.getDateCount(3,(byte)1));
            modelAndView.addObject("returnMonth",borrowService.getDateCount(4,(byte)1));

            modelAndView.addObject("reserveTotal",reserveService.getReserveCount(0));
            modelAndView.addObject("reserveToday",reserveService.getReserveCount(1));
            modelAndView.addObject("reserveYesterday",reserveService.getReserveCount(2));
            modelAndView.addObject("reserveWeek",reserveService.getReserveCount(3));
            modelAndView.addObject("reserveMonth",reserveService.getReserveCount(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}

