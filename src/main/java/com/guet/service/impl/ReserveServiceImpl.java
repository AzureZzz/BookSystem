package com.guet.service.impl;

import com.guet.dao.ReserveMapper;
import com.guet.entity.Reserve;
import com.guet.entity.ReserveKey;
import com.guet.service.IReserveService;
import com.guet.utils.ReturnMessage;
import com.guet.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReserveServiceImpl implements IReserveService {

    @Autowired
    private ReserveMapper reserveMapper;

    @Override
    public void addReserve(Reserve reserve) throws Exception {
        reserveMapper.insert(reserve);
    }

    @Override
    public Reserve getReserve(ReserveKey reserveKey) throws Exception {
        return reserveMapper.selectByPrimaryKey(reserveKey);
    }

    @Override
    public void deleteReserve(ReserveKey reserveKey) throws Exception {
        reserveMapper.deleteByPrimaryKey(reserveKey);
    }

    @Override
    public void updateReserve(Reserve reserve) throws Exception {
        reserveMapper.updateByPrimaryKeySelective(reserve);
    }

    @Override
    public Map<String, Object> getReserves(String aoData) throws Exception {
        String[] cols = {"username","username","book_num","bor_time"};
        Map<String,Object> params = ServiceUtils.getAoDataParams(aoData);
        String sEcho = (String) params.get("sEcho");
        int iDisplayStart = (int) params.get("iDisplayStart");
        int iDisplayLength = (int) params.get("iDisplayLength");
        String column = cols[(int) params.get("columnIndex")];
        boolean sortDir = (boolean) params.get("sortDir");
        String keyWord = (String) params.get("keyWord");

        int totalCount = reserveMapper.selectAllCount();
        List<Reserve> reserves =  reserveMapper.selectReserves(iDisplayStart,iDisplayLength,sortDir,column,keyWord);
        int displayCount = reserves.size();
        return ReturnMessage.dataTablesResult(sEcho,totalCount,displayCount,reserves);
    }

    @Override
    public int getReserveCount(int type) throws Exception {
        Date date = new Date();
        switch (type){
            case 0:return reserveMapper.selectAllCount();
            case 1:return reserveMapper.selectCountDate(date,ServiceUtils.addAndSubtractDaysByCalendar(date,1));
            case 2:return reserveMapper.selectCountDate(ServiceUtils.addAndSubtractDaysByCalendar(date,-1),date);
            case 3:return reserveMapper.selectCountDate(ServiceUtils.addAndSubtractDaysByCalendar(date,-7),date);
            case 4:return reserveMapper.selectCountDate(ServiceUtils.addAndSubtractDaysByCalendar(date,-30),date);
        }
        return 0;
    }

    @Override
    public List<Integer> getWeekCount() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int week = ServiceUtils.dayForWeek(df.format(new Date()));
        Date date = ServiceUtils.addAndSubtractDaysByCalendar(new Date(),1-week);
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<7;i++){
            result.add(reserveMapper.selectCountDate(date,ServiceUtils.addAndSubtractDaysByCalendar(date,1)));
            date = ServiceUtils.addAndSubtractDaysByCalendar(date,1);
        }
        System.out.println(result);
        return result;
    }
}
