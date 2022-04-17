package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    //在业务逻辑层中，一定会有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        //根据传入的用户到DB中查询相应的对象
        //若有条件，则一定要创建AdminExample的对象来封装条件
        AdminExample example = new AdminExample();
        //添加用户名条件
        example.createCriteria().andANameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size()>0){
            //若查到用户对象，再进行密码的比对
            Admin admin = list.get(0);
            String miPwd = MD5Util.getMD5(pwd);
            if(miPwd.equals(admin.getaPass())){
                return admin;
            }
        }

        return null;
    }
}
