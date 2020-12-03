package cn.com.anno;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("MyAspect")
@Aspect
public class MyAspect { //切面类
    @Before("execution(* cn.com.anno.*.*(..))")
    public void before(){
        System.out.println("前置增强...");
    }

    @AfterReturning("execution(* cn.com.anno.*.*(..))")
    public void afterReturning(){
        System.out.println("后置增强...");
    }

    //ProceedingJoinPoint：正在执行的连接点===>切点
    @Around("execution(* cn.com.anno.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前增强......");
        Object proceed = pjp.proceed(); //切点方法
        System.out.println("环绕后增强......");
        return proceed;
    }

    @AfterThrowing("execution(* cn.com.anno.*.*(..))")
    public void throwing(){
        System.out.println("异常抛出通知...");
    }

    @After("execution(* cn.com.anno.*.*(..))")
    public void after(){
        System.out.println("最终通知...");
    }
}
