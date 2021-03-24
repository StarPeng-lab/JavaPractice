package cn.test.service.impl;

import cn.test.dao.impl.UserDaoImpl;
import cn.test.service.UserService;

public class UserServiceImpl implements UserService {
    public UserDaoImpl dao;

    public void setUserDao(UserDaoImpl dao){
        this.dao = dao;
    }

    public void show(){
        dao.save();
    }
}
