package com.guet.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnMessage{

    public static Map<String,Object> getResult(int code, String msg, Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }

    public static Map<String,Object> dataTablesResult(String sEcho,int iTotalRecords,
                                                      int iTotalDisplayRecords,Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("sEcho",sEcho);
        map.put("iTotalRecords",iTotalRecords);
        map.put("iTotalDisplayRecords",iTotalDisplayRecords);
        map.put("data",data);
        return map;
    }
}
