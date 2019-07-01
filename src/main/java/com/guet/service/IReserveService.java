package com.guet.service;

import com.guet.entity.Reserve;
import com.guet.entity.ReserveKey;

import java.util.List;
import java.util.Map;

public interface IReserveService {

    void addReserve(Reserve reserve)throws Exception;

    Reserve getReserve(ReserveKey reserveKey)throws Exception;

    void deleteReserve(ReserveKey reserveKey)throws Exception;

    void updateReserve(Reserve reserve)throws Exception;

    Map<String,Object> getReserves(String aoData)throws Exception;

    int getReserveCount(int type)throws Exception;

    List<Integer> getWeekCount()throws Exception;
}
