package com.guet.service.impl;

import com.guet.dao.AdminMapper;
import com.guet.entity.Admin;
import com.guet.service.IAdminService;
import com.guet.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) throws Exception {
        Admin admin = adminMapper.selectLogin(username,ServiceUtils.md5(password));
        admin.setPassword("");
        return admin;
    }

    @Override
    public boolean updatePassword(String admin,String oldPassword, String newPassword) throws Exception {
        Admin a = adminMapper.selectByPrimaryKey(admin);
        if(ServiceUtils.md5(oldPassword.trim()).equals(a.getPassword())){
            a.setPassword(ServiceUtils.md5(newPassword));
            adminMapper.updateByPrimaryKeySelective(a);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Admin getAdmin(String admin) throws Exception {
        Admin a = adminMapper.selectByPrimaryKey(admin);
        a.setPassword("");
        return a;
    }
}
