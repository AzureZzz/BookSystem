package com.guet.dao;

import com.guet.entity.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoMapper extends ICommonMapper<BookInfo,String> {

    List<BookInfo> selectBookInfos(@Param("offest") int offest, @Param("size") int size,
                             @Param("sortDir") boolean sortDir, @Param("column") String column, @Param("keyWord") String keyWord)throws Exception;

    int selectBookInfosCount(@Param("keyWord") String keyWord)throws Exception;

}