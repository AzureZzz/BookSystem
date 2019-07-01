package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.Reader;
import com.guet.service.IReaderService;
import com.guet.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reader/login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestBody Map<String,String> body,
                                    HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String username = body.get("username").trim();
        String password = body.get("password").trim();
        if ("".equals(username) || "".equals(password)) {
            return ReturnMessage.getResult(1,"用户名或密码为空！",null);
        }
        try {
            Reader reader = readerService.login(username, password);
            if (reader == null) {
                return ReturnMessage.getResult(1,"用户名或密码错误！",null);
            }
            request.getSession().setAttribute("reader",reader.getUsername());
            return ReturnMessage.getResult(0,"登陆成功！",reader);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"登陆失败！",null);
        }
    }

    @AuthAdmin
    @RequestMapping(value = "reader/logout",method = RequestMethod.GET)
    public void logout(HttpServletRequest request,HttpServletResponse response) {
        request.getSession().removeAttribute("reader");
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reader",method = RequestMethod.PUT)
    public Map<String,Object> updateReader(@RequestBody Reader reader) {
        try {
            readerService.updateInfo(reader);
            return ReturnMessage.getResult(0,"修改成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"修改失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reader/{username}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteReader(@PathVariable("username")String username) {
        try {
            readerService.deleteReader(username);
            return ReturnMessage.getResult(0,"删除成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"删除失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reader/changepassword",method = RequestMethod.PUT)
    public Map<String,Object> changePassword(@RequestBody Map<String ,Object> body, HttpServletRequest request){
        String newPassword = (String) body.get("newPassword");
        String reader = (String) body.get("username");
        try {
            readerService.updatePassword(reader,newPassword);
            return ReturnMessage.getResult(0,"修改成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"修改失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reader/modifypassword",method = RequestMethod.PUT)
    public Map<String,Object> modifyPassword(@RequestBody Map<String ,Object> body,
                                             HttpServletRequest request){
        String password = (String) body.get("password");
        String newPassword = (String) body.get("newPassword");
        String reader = (String) body.get("username");
        try {
            if(reader == null) {
                if(request.getSession().getAttribute("reader")==null){
                    return ReturnMessage.getResult(1,"无权限！",null);
                }
                reader = (String) request.getSession().getAttribute("reader");
                readerService.updatePassword(reader,password,newPassword);
            }else{
                if(request.getSession().getAttribute("admin")==null){
                    return ReturnMessage.getResult(1,"无权限！",null);
                }
                readerService.updatePassword(reader,newPassword);
            }
            return ReturnMessage.getResult(0,"修改成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"修改失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Map<String,Object> modifyPassword(@RequestBody Reader reader){
        reader.setBalance(0.0);
        reader.setSignTime(new Date());
        try {
            readerService.register(reader);
            return ReturnMessage.getResult(0,"注册成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"注册失败！",null);
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "readers",method = RequestMethod.GET)
    public Map<String,Object> getBookInfos(@RequestParam String aoData) {
        try {
            return readerService.getReaders(aoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
