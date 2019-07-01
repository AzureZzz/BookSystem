package com.guet.service;

import com.guet.entity.Borrow;
import com.guet.entity.BorrowKey;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IBorrowService {

    void addBorrow(Borrow borrow)throws Exception;

    Borrow getBorrow(BorrowKey borrowKey)throws Exception;

    void deleteBorrow(BorrowKey borrowKey)throws Exception;

    void updateBorrow(Borrow borrow)throws Exception;

    boolean renew(BorrowKey borrowKey)throws Exception;

    Map<String,Object> getBorrows(String aoData)throws Exception;

    Map<String,Object> getReaderBorrows(String username,String aoData)throws Exception;

    int getAllCount(Byte state)throws Exception;

    int getDateCount(int dateType,Byte state)throws Exception;

    int getReaderCount(String username,Byte state)throws Exception;

    List<String> getTenReader()throws Exception;

    List<String> getTenBook()throws Exception;

    List<Borrow> getExcelBorrows()throws Exception;

    void returnBook(BorrowKey borrowKey)throws Exception;

    List<Integer> getBorrowWeekCount()throws Exception;

    List<Integer> getReturnWeekCount()throws Exception;
}
