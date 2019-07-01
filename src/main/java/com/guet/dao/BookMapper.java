package com.guet.dao;

import com.guet.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper extends ICommonMapper<Book,String>{

    void deleteByCallNum(@Param("callNum")String callNum)throws Exception;

    int getCountBook(@Param("callNum")String callNum)throws Exception;

    int getCountBookState(@Param("callNum")String callNum,@Param("state")Byte state)throws Exception;

    List<Book> selectAllBooks(@Param("offest") int offest, @Param("size") int size,
                                   @Param("sortDir") boolean sortDir, @Param("column") String column, @Param("keyWord") String keyWord)throws Exception;

    List<Book> selectBooks(@Param("callNum")String callNum,@Param("offest") int offest, @Param("size") int size,
                           @Param("sortDir") boolean sortDir, @Param("column") String column, @Param("keyWord") String keyWord)throws Exception;

    void resetState(@Param("username")String username)throws Exception;
}