import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class DataSourceTest {

    ResourceBundle rb = ResourceBundle.getBundle("jdbc");//默认加载类路径下的.properties文件，书写文件名即可
    String driver = rb.getString("jdbc.Driver");
    String url = rb.getString("jdbc.url");
    String username = rb.getString("jdbc.username");
    String password = rb.getString("jdbc.password");

    /**
     * 手动创建c3p0数据源
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        Connection conn = dataSource.getConnection();
        System.out.println(conn); //com.mchange.v2.c3p0.impl.NewProxyConnection@13fee20c
        conn.close();
    }

    /**
     * 手动创建Druid数据源
     * @throws Exception
     */
    @Test
    public void test2() throws Exception{
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }

    @Test
    public void test3(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ComboPooledDataSource dataSource_c3p0 = (ComboPooledDataSource) app.getBean("dataSource_c3p0");
        System.out.println("c3p0："+dataSource_c3p0);
        DruidDataSource dataSource_druid = (DruidDataSource) app.getBean("dataSource_druid");
        System.out.println("druid："+dataSource_druid);
    }
}
