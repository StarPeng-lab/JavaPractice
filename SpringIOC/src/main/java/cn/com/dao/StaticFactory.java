package cn.com.dao;

import cn.com.dao.impl.TestDaoImpl;

// 工厂静态方法实例化
public class StaticFactory {
    public static TestDao getTestDao(){
        return new TestDaoImpl();
    }
}
