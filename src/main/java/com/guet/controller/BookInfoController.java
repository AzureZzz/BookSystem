package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.BookInfo;
import com.guet.service.IBookInfoService;
import com.guet.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "admin")
public class BookInfoController {

    @Autowired
    private IBookInfoService bookInfoService;

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "/bookInfo",method = RequestMethod.POST)
    public Map<String,Object> addBookInfo(@RequestBody BookInfo bookInfo)
    {
        bookInfo.setCover("");
        try {
            bookInfoService.addBookInfo(bookInfo);
            return ReturnMessage.getResult(0,"入库成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"入库失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "/bookInfo",method = RequestMethod.PUT)
    public Map<String,Object> updateBookInfo(@RequestBody BookInfo bookInfo) {
        System.out.println(bookInfo.getPubTime());
        try {
            bookInfoService.updateBookInfo(bookInfo);
            return ReturnMessage.getResult(0,"更新成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"更新失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "/bookInfo/{call_num}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteBookInfo(@PathVariable("call_num")String callNum)
    {
        try {
            bookInfoService.deleteBookInfo(callNum);
            return ReturnMessage.getResult(0,"删除成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"删除失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "/bookInfo/{call_num}",method = RequestMethod.GET)
    public Map<String,Object> addBookInfo(@PathVariable("call_num")String callNum)
    {
        try {
            BookInfo bookInfo = bookInfoService.getBookInfo(callNum);
            return ReturnMessage.getResult(0,"查询成功！",bookInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"查询失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "/bookInfos",method = RequestMethod.GET)
    public Map<String,Object> getBookInfos(@RequestParam String aoData) {
        try {
            return bookInfoService.getBookInfos(aoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
