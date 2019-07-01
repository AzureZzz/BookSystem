package com.guet.dao;

import com.guet.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends ICommonMapper<Admin,String>{
    Admin selectLogin(@Param("admin")String admin,@Param("password")String password)throws Exception;
}