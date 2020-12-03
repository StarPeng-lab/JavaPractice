package cn.com.anno;

import org.springframework.stereotype.Component;

@Component("Target")
public class Target implements TargetInterface {
    @Override
    public void save() {
        System.out.println("target......");
    }
}
