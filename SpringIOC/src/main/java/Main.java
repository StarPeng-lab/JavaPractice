import cn.com.config.SpringConfiguration;
import cn.com.dao.TestDao;
import cn.com.service.TestService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        TestService service = (TestService) app.getBean("TestService");
        System.out.println(service); //cn.com.dao.impl.TestDaoImpl@26be92ad
        service.test();

        /*TestDao test1 = (TestDao)app.getBean("test1");

         //dao...*/
       // ((ClassPathXmlApplicationContext)app).close(); //关闭，销毁bean对象
    }
}
