package com.cn.dao;

public interface AccountDao {
    public void in(String inman, int money);
    public void out(String outman, int money);
}
