package com.cn.dao.impl;

import com.cn.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
   private JdbcTemplate template;

    @Override
    public void in(String inman, int money) {
        template.update("update u set money = money + ? where name = ?",money,inman);
    }

    @Override
    public void out(String outman, int money) {
        template.update("update u set money = money - ? where name = ?",money,outman);
    }
}
