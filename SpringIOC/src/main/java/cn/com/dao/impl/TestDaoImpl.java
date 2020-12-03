package cn.com.dao.impl;

import cn.com.dao.TestDao;
import cn.com.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Properties;

//无参构造实例化
@Component("test1")
public class TestDaoImpl implements TestDao {
    @Override
    public void init(){
        System.out.println("init...");
    }

    private List<String> strList;
    private Map<String, User> userMap;
    private Properties properties;

    public void setStrList(List<String> strList){
        this.strList = strList;
    }
    public void setUserMap(Map<String,User> userMap){
        this.userMap = userMap;
    }
    public void setProperties(Properties properties){
        this.properties = properties;
    }

    @Override
    public void test() {
        System.out.println("dao...");
        System.out.println(strList);
        System.out.println(userMap);
        System.out.println(properties);
        System.out.println("dao11...");
    }

     @Override
    public void destory(){
        System.out.println("destory...");
    }
}
