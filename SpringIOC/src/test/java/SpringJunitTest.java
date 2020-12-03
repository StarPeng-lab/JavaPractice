import cn.com.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class) //1、使用Spring提供的内核代替原来的junit运行
@ContextConfiguration(value="classpath:applicationContext.xml") //2、指定配置文件；value可以省略
//@ContextConfiguration(classes={SpringConfiguration.class} //指定配置类
public class SpringJunitTest {

    @Autowired //3、根据类型自动注入依赖（需保证配置中同一类型只有一个对象）
    @Qualifier("test2") //这里的test2是配置文件中，TestService类的 bean id
    private TestService service;

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        service.test();
        System.out.println(dataSource.getConnection());
    }
}
