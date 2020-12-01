import cn.com.dao.TestDao;
import cn.com.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestDao test1 = (TestDao)app.getBean("test1");

        TestService test2 = (TestService) app.getBean("test2");
        System.out.println(test2); //cn.com.dao.impl.TestDaoImpl@26be92ad
        test2.test(); //dao...
       // ((ClassPathXmlApplicationContext)app).close(); //关闭，销毁bean对象
    }
}
