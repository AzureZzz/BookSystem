package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.Borrow;
import com.guet.entity.BorrowKey;
import com.guet.service.IBookService;
import com.guet.service.IBorrowService;
import com.guet.service.IReaderService;
import com.guet.utils.ReturnMessage;
import com.guet.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class BorrowController {

    @Autowired
    private IBorrowService borrowService;

    @Autowired
    private IReaderService readerService;

    @Autowired
    private IBookService bookService;

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "borrow",method = RequestMethod.POST)
    public Map<String,Object> addBorrow(@RequestBody Borrow borrow){
        borrow.setBorTime(new Date());
        borrow.setState((byte)0);
        borrow.setReNew((byte)0);
        borrow.setRetTime(ServiceUtils.addAndSubtractDaysByCalendar(new Date(),30));
        try {
            if(readerService.getReader(borrow.getUsername())==null){
                return ReturnMessage.getResult(1,"用户不存在！",null);
            }
            if(bookService.getBook(borrow.getBookNo())==null){
                return ReturnMessage.getResult(1,"该书不存在！",null);
            }
            if(bookService.getBook(borrow.getBookNo()).getState() != 0){
                return ReturnMessage.getResult(1,"该书已被借出！",null);
            }

            borrowService.addBorrow(borrow);
            return ReturnMessage.getResult(0,"借书成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"借书失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "borrow/{username}/{bookNo}/{borTime}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteBorrow(@PathVariable("username")String username,
                                        @PathVariable("bookNo") String bookNo,
                                           @PathVariable("borTime")String borTime){
        BorrowKey borrowKey = new BorrowKey();
        borrowKey.setBookNo(bookNo);
        borrowKey.setUsername(username);
        borrowKey.setBorTime(new Date(Long.valueOf(borTime)));
        try {
            borrowService.deleteBorrow(borrowKey);
            return ReturnMessage.getResult(0,"删除成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"删除失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "renew/{bookNo}/{borTime}",method = RequestMethod.GET)
    public Map<String,Object> renew(@PathVariable("bookNo")String bookNo,
                                    @PathVariable("borTime")String borTime,
                                    HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("reader");
        BorrowKey borrowKey = new BorrowKey();
        borrowKey.setUsername(username);
        borrowKey.setBookNo(bookNo);
        borrowKey.setBorTime(new Date(Long.valueOf(borTime)));
        try {
            borrowService.renew(borrowKey);
            return ReturnMessage.getResult(0,"续借成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"续借失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "borrow",method = RequestMethod.PUT)
    public Map<String,Object> updateBorrow(@RequestBody Borrow borrow, HttpServletRequest request){
        try {
            borrowService.updateBorrow(borrow);
            return ReturnMessage.getResult(0,"修改成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"修改失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "borrows",method = RequestMethod.GET)
    public Map<String,Object> getBookInfos(@RequestParam String aoData) {
        try {
            return borrowService.getBorrows(aoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "returnBook",method = RequestMethod.POST)
    public Map<String,Object> returnBook(@RequestBody BorrowKey borrowKey) {
        try {
            borrowService.returnBook(borrowKey);
            return ReturnMessage.getResult(0,"还书完成！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"还书失败！",null);
        }
    }
}
