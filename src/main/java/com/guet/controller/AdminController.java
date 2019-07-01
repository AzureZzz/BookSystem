package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.Admin;
import com.guet.service.IAdminService;
import com.guet.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestBody Map<String,String> body,
                                    HttpServletRequest request) {
        String admin = body.get("admin").trim();
        String password = body.get("password").trim();
        if ("".equals(admin) || "".equals(password)) {
            return ReturnMessage.getResult(1,"用户名或密码为空！",null);
        }
        try {
            Admin a = adminService.login(admin, password);
            if (a == null) {
                return ReturnMessage.getResult(1,"用户名或密码错误！",null);
            }
            request.getSession().setAttribute("admin",a.getAdmin());
            return ReturnMessage.getResult(0,"登陆成功！",a);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"登陆失败！",null);
        }
    }

    @AuthAdmin
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public void logout(HttpServletRequest request,HttpServletResponse response) {
        request.getSession().removeAttribute("admin");
        try {
            response.sendRedirect("/admin/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/modifypassword",method = RequestMethod.PUT)
    public Map<String,Object> modifyPassword(@RequestBody Map<String ,Object> body,
                                             HttpServletRequest request){
        String password = (String) body.get("password");
        String newPassword = (String) body.get("newPassword");
        String admin = (String) request.getSession().getAttribute("admin");
        try {
            adminService.updatePassword(admin,password,newPassword);
            return ReturnMessage.getResult(0,"修改成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMessage.getResult(1,"修改失败！",null);
    }
}
