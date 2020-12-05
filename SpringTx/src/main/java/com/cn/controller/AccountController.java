package com.cn.controller;

import com.cn.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountController {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //AccountService service = (AccountService) app.getBean(accountService); //利用bean id获得bean对象
        AccountService service = app.getBean(AccountService.class); //利用 类型 获得bean对象
        service.transfer("zhangsan","lisi",200);
    }
}
