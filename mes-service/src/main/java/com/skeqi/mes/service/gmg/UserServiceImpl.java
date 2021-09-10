package com.skeqi.mes.service.gmg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.chenj.srm.req.CMesUserTReq;
import com.skeqi.mes.util.PageInfoUtil;
import com.skeqi.mes.util.Rjson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.gmg.UserDao;
import com.skeqi.mes.pojo.CMesUserT;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public Integer GetUserInfoByCondition(String username, String userpwd) {
        // TODO Auto-generated method stub
        return userDao.GetUserInfoByCondition(username, userpwd);
    }


    @Override
    public CMesUserT GetAllUserInfo() {
        // TODO Auto-generated method stub
        return userDao.GetAllUserInfo();
    }


    @Override
    public CMesUserT findByName(String userName) {
        // TODO Auto-generated method stub
        return userDao.findByName(userName);
    }


    @Override
    public String findByStatus(String userName) {
        // TODO Auto-generated method stub
        return userDao.findByStatus(userName);
    }


    @Override
    public void updateStatus(String loginStatus, String userName) {
        // TODO Auto-generated method stub
        userDao.updateStatus(loginStatus, userName);
    }

    @Override
    public Rjson findUserList(CMesUserTReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(userDao.findUserList(req)));
    }

}
