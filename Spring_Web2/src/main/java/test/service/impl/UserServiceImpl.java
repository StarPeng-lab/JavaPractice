package test.service.impl;

import test.dao.impl.UserDaoImpl;
import test.service.UserService;

public class UserServiceImpl implements UserService {
    public UserDaoImpl dao;

    public void setUserDao(UserDaoImpl dao){
        this.dao = dao;
    }

    public void show(){
        dao.save();
    }
}
