package cn.com.service.impl;

import cn.com.dao.TestDao;
import cn.com.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//<bean id="test2" class="cn.com.service.impl.TestServiceImpl">
@Component("test2")
public class TestServiceImpl implements TestService {

    // <constructor-arg name="dao" ref="test1"></constructor-arg>
    @Autowired
    @Qualifier("test1")
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
