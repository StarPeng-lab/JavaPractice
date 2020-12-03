package jdbc;

import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

public class JdbcTemplateTest {
    @Test
    public void test() throws PropertyVetoException {
        //通过c3p0连接池，创建数据源对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        //1、创建模版
        JdbcTemplate template = new JdbcTemplate();
        //2、设置数据源对象
        template.setDataSource(dataSource);
        //3、执行操作
        int row = template.update("insert into u values(?,?)", "zhangsan", 15);
        System.out.println(row);
    }
}
