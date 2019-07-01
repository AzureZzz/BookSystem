package com.guet.dao;

import com.guet.entity.Reserve;
import com.guet.entity.ReserveKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ReserveMapper extends ICommonMapper<Reserve,ReserveKey> {

    int selectCountDate(@Param("startDate")Date startDate, @Param("endDate")Date endDate)throws Exception;

    List<Reserve> selectReserves(@Param("offest") int offest, @Param("size") int size,
                                   @Param("sortDir") boolean sortDir, @Param("column") String column, @Param("keyWord") String keyWord)throws Exception;

}