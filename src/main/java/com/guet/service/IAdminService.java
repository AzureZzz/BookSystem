package com.guet.service;

import com.guet.entity.Admin;

public interface IAdminService {

    Admin login(String username,String password)throws Exception;

    boolean updatePassword(String admin,String oldPassword,String newPassword)throws Exception;

    Admin getAdmin(String admin)throws Exception;
}
