package cn.com.aop;

public class Target implements TargetInterface {
    @Override
    public void save() {
        System.out.println("target......");
    }
}
