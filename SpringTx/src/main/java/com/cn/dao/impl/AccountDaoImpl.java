package com.cn.dao.impl;

import com.cn.dao.AccountDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate template;
    public void setJdbcTemplate(JdbcTemplate template){
        this.template = template;
    }

    @Override
    public void in(String inman, int money) {
        template.update("update u set money = money + ? where name = ?",money,inman);
    }

    @Override
    public void out(String outman, int money) {
        template.update("update u set money = money - ? where name = ?",money,outman);
    }
}
