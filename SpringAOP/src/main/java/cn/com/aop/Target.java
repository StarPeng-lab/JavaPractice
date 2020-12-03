package cn.com.aop;

public class Target implements TargetInterface {
    @Override
    public void save() {
        int i = 2/0;
        System.out.println("target......");
    }
}
