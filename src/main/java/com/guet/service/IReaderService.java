package com.guet.service;

import com.guet.entity.Reader;
import com.guet.entity.ReaderRow;

import java.util.List;
import java.util.Map;

public interface IReaderService {

    Reader login(String username,String password)throws Exception;

    Reader getReader(String username)throws Exception;

    boolean updatePassword(String username,String newPassword)throws Exception;

    boolean updatePassword(String username,String oldPassword,String newPassword)throws Exception;

    void updateInfo(Reader reader)throws Exception;

    void register(Reader reader)throws Exception;

    void deleteReader(String username)throws Exception;

    Map<String,Object> getReaders(String aoData)throws Exception;

    int getReaderCount(int type)throws Exception;

    List<ReaderRow> getExcelReaders()throws Exception;

    List<Integer> getWeekCount()throws Exception;
}
