package com.guet.dao;

import com.guet.entity.Reader;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ReaderMapper extends ICommonMapper<Reader,String>{

     Reader selectLogin(@Param("username")String username, @Param("password")String password)throws Exception;

     int selectCountDate(@Param("startDate")Date startDate,@Param("endDate")Date endDate)throws Exception;

     List<Reader> selectReaders(@Param("offest") int offest, @Param("size") int size,
                                  @Param("sortDir") boolean sortDir, @Param("column") String column, @Param("keyWord") String keyWord)throws Exception;

}