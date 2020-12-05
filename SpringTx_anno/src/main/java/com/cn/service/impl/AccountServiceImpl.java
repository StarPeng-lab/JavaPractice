package com.cn.service.impl;

import com.cn.dao.AccountDao;
import com.cn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("accountService")
@Transactional(isolation = Isolation.READ_COMMITTED) //当前类下的所有方法都增强事务
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao dao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED) //在需要增强事务的方法上添加注解，就近原则，以方法上配置的注解为主
    public void transfer(String outman, String inman, int money) {
        dao.out(outman,money);
        int i = 5/0;
        dao.in(inman,money);
    }
}
