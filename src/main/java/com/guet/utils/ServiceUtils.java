package com.guet.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServiceUtils {

    public static String md5(String message) {

        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(message.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public static Integer getNowTimeStr(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
//        return Integer.parseInt(dateFormat.format(new Date()));
        return Integer.parseInt(new Date().getTime()+"");
    }


    public static int  dayForWeek(String pTime) throws  Exception {
        SimpleDateFormat format = new  SimpleDateFormat("yyyy-MM-dd" );
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int  dayForWeek = 0 ;
        if (c.get(Calendar.DAY_OF_WEEK) == 1 ){
            dayForWeek = 7 ;
        }else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1 ;
        }
        return dayForWeek;
    }

    public static Date addAndSubtractDaysByCalendar(Date dateTime, int n){
        java.util.Calendar calstart = java.util.Calendar.getInstance();
        calstart.setTime(dateTime);
        calstart.add(java.util.Calendar.DAY_OF_WEEK, n);
        return calstart.getTime();
    }

    public static Map<String,Object> getAoDataParams(String aoData){
        Map<String,Object> map = new HashMap<>();
        JSONArray jsonArray = JSONArray.parseArray(aoData);
        String sEcho = null;
        int iDisplayStart = 0;
        int iDisplayLength = 0;
        int columnIndex = 0;
        boolean sortDir = true;
        String keyWord = null;
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.get("name").equals("sEcho"))
                sEcho = jsonObject.get("value").toString();
            if (jsonObject.get("name").equals("iDisplayStart"))
                iDisplayStart = jsonObject.getIntValue("value");
            if (jsonObject.get("name").equals("iDisplayLength"))
                iDisplayLength = jsonObject.getIntValue("value");
            if(jsonObject.get("name").equals("iSortCol_0"))
                columnIndex = (int) jsonObject.get("value");
            if(jsonObject.get("name").equals("sSortDir_0")){
                if(jsonObject.get("value").equals("desc")){
                    sortDir = true;
                }else{
                    sortDir = false;
                }
            }
            if(jsonObject.get("name").equals("sSearch"))
                keyWord = jsonObject.getString("value");
        }
        map.put("sEcho",sEcho);
        map.put("iDisplayLength",iDisplayLength);
        map.put("iDisplayStart",iDisplayStart);
        map.put("columnIndex",columnIndex );
        map.put("sortDir",sortDir);
        map.put("keyWord",keyWord);
        return map;
    }

}
