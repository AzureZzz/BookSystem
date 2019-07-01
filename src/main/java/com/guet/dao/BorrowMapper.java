package com.guet.dao;

import com.guet.entity.Borrow;
import com.guet.entity.BorrowKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BorrowMapper extends ICommonMapper<Borrow,BorrowKey>{

    int selectCountState(@Param("state") Byte state)throws Exception;

    int selectAllCountReader(@Param("username")String username)throws Exception;

    int selectCountStateDate(@Param("state") Byte state, @Param("startDate") Date startDate,
                             @Param("endDate") Date endDate)throws Exception;

    int selectReaderCountState(@Param("state") Byte state,@Param("username")String username)throws Exception;

    int selectCountStateReader(@Param("username") String username, @Param("state") Byte state)throws Exception;

    int selectUnReturnCount()throws Exception;

    int selectReaderUnReturnCount(@Param("username") String username)throws Exception;

    int selectBookCount(@Param("callNum")String callNum)throws Exception;

    List<Borrow> selectBorrows(@Param("offest") int offest, @Param("size") int size,
                                   @Param("sortDir") boolean sortDir, @Param("column") String column, @Param("keyWord") String keyWord)throws Exception;

    List<Borrow> selectReaderBorrows(@Param("username")String username,@Param("offest") int offest, @Param("size") int size,
                               @Param("sortDir") boolean sortDir, @Param("column") String column, @Param("keyWord") String keyWord)throws Exception;


}