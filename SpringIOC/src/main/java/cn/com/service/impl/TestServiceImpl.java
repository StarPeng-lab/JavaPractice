package cn.com.service.impl;

import cn.com.dao.TestDao;
import cn.com.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

//<bean id="test2" class="cn.com.service.impl.TestServiceImpl">
@Component("TestService")
public class TestServiceImpl implements TestService {

    // <constructor-arg name="dao" ref="test1"></constructor-arg>
    @Autowired
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
