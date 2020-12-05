package com.cn.service.impl;

import com.cn.dao.AccountDao;
import com.cn.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao dao;
    public void setAccountDao(AccountDao dao){
        this.dao = dao;
    }

    @Override
    public void transfer(String outman, String inman, int money) {
        dao.out(outman,money);
        //int i = 5/0; //如果不配置事务，则执行到这行后报错，数据表中，outman扣了钱，inman却没有加钱
        dao.in(inman,money);
    }
}
