package cn.com.jdk;

public class Advice {
    public void before(){
        System.out.println("before...");
    }
    public void after(){
        System.out.println("after...");
    }
}
