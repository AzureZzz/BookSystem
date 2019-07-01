package com.guet.controller;

import com.guet.annotation.AuthAdmin;
import com.guet.entity.Reserve;
import com.guet.entity.ReserveKey;
import com.guet.service.IReserveService;
import com.guet.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ReserveController {

    @Autowired
    private IReserveService reserveService;

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reserve",method = RequestMethod.POST)
    public Map<String,Object> addReserve(@RequestBody Reserve reserve,
                                            HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("reader");
        reserve.setUsername(username);
        try {
            reserveService.addReserve(reserve);
            return ReturnMessage.getResult(0,"预订成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"预订失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reserve",method = RequestMethod.PUT)
    public Map<String,Object> updateReserve(@RequestBody Reserve reserve,
                                            HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("reader");
        reserve.setUsername(username);
        try {
            reserveService.updateReserve(reserve);
            return ReturnMessage.getResult(0,"预订时间修改成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"预订时间修改失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reserve/{bookNo}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteReserve(@PathVariable("bookNo")String bookNo,
                                           HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("reader");
        ReserveKey reserveKey = new ReserveKey();
        reserveKey.setBookNo(bookNo);
        reserveKey.setUsername(username);
        try {
            reserveService.deleteReserve(reserveKey);
            return ReturnMessage.getResult(0,"取消成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"取消失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reserve/{username}/{bookNo}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteReserve(@PathVariable("bookNo")String bookNo,
                                                  @PathVariable("username")String username){
        ReserveKey reserveKey = new ReserveKey();
        reserveKey.setBookNo(bookNo);
        reserveKey.setUsername(username);
        try {
            reserveService.deleteReserve(reserveKey);
            return ReturnMessage.getResult(0,"删除成功！",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.getResult(1,"删除失败！",null);
        }
    }

    @AuthAdmin
    @ResponseBody
    @RequestMapping(value = "reserves",method = RequestMethod.GET)
    public Map<String,Object> getBookInfos(@RequestParam String aoData) {
        try {
            return reserveService.getReserves(aoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
