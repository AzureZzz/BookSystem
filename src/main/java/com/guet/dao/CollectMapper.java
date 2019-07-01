package com.guet.dao;

import com.guet.entity.CollectKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper extends ICommonMapper<CollectKey,CollectKey>{

    int selectReaderCount(@Param("username")String username)throws Exception;

    List<CollectKey> selectReaderCollects(@Param("username")String username, @Param("offest") int offest, @Param("size") int size,
                                         @Param("sortDir") boolean sortDir, @Param("column") String column, @Param("keyWord") String keyWord)throws Exception;

    int selectCountBook(@Param("callNum")String callNum)throws Exception;
}