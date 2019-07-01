package com.guet.dao;

import java.io.Serializable;
import java.util.List;

public interface ICommonMapper<T,PK extends Serializable>{

    int deleteByPrimaryKey(PK id) throws Exception;

    int insert(T record) throws Exception;

    int insertSelective(T record) throws Exception;

    T selectByPrimaryKey(PK id) throws Exception;

    int selectAllCount()throws Exception;

    List<T> selectAll() throws Exception;

    int updateByPrimaryKeySelective(T record) throws Exception;

    int updateByPrimaryKeyWithBLOBs(T record) throws Exception;

    int updateByPrimaryKey(T record) throws Exception;
}

