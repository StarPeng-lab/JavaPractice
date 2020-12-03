package cn.com.cglib;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class ProxyTest {
    public static void main(String[] args){
        //目标对象
        final Target target = new Target();
        //增强对象
        final Advice advice = new Advice();

        //返回值 ，即动态生成的代理对象，基于cglib
        //1、创建增强器
        Enhancer enhancer = new Enhancer();
        //2、设置父类（目标）
        enhancer.setSuperclass(Target.class);
        //3、设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                advice.before(); //前置增强
                Object invoke = method.invoke(target, args);//通过反射，执行【目标对象】的方法
                advice.after(); //后置增强
                return invoke;
            }
        });

        //4、创建代理对象
        Target proxy = (Target)enhancer.create();

        //调用代理对象的方法
        proxy.save();
    }
}
