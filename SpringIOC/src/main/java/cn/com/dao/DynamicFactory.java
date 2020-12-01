package cn.com.dao;

import cn.com.dao.impl.TestDaoImpl;

//工厂实例方法实例化
public class DynamicFactory {
    public TestDao getTestDao(){
        return new TestDaoImpl();
    }
}
