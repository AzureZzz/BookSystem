package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.CollectKey;
import com.guet.service.ICollectService;
import com.guet.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CollectController {
    
    @Autowired
    private ICollectService collectService;

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "collect",method = RequestMethod.POST)
    public Map<String,Object> deleteCollect(@RequestBody CollectKey collectKey,
                                           HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("reader");
        collectKey.setUsername(username);
        try {
            collectService.addCollect(collectKey);
            return ReturnMessage.getResult(0,"收藏成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"收藏失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "collect/{bookNo}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteCollect(@PathVariable("bookNo") String bookNo,
                                           HttpServletRequest request){
        CollectKey collectKey = new CollectKey();
        String username = (String) request.getSession().getAttribute("reader");
        collectKey.setBookNo(bookNo);
        collectKey.setUsername(username);
        try {
            collectService.deleteCollect(collectKey);
            return ReturnMessage.getResult(0,"取消成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"取消失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "collects",method = RequestMethod.GET)
    public Map<String,Object> getCollects(@RequestParam String aoData, HttpServletRequest request){
        try {
            String username = (String) request.getSession().getAttribute("reader");
            return collectService.getReaderCollects(username,aoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
