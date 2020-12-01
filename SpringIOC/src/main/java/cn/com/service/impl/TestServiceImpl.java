package cn.com.service.impl;

import cn.com.dao.TestDao;
import cn.com.service.TestService;

public class TestServiceImpl implements TestService {
    private TestDao dao ;
    //set方法注入依赖
    /*
    public void setTestDao(TestDao dao){
        this.dao = dao;
    }
    */
    public TestServiceImpl(TestDao dao){
        this.dao = dao;
    }
    public TestServiceImpl(){}

    @Override
    public void test() {
        dao.test();
    }
}
