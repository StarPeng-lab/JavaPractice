package jdbc;

import domain.U;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcCrudTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1(){ //插入
        //update(String sql,Object... args)
        int row = jdbcTemplate.update("insert into u values(?,?)", "xiaohong", 17);
        System.out.println(row); //1
    }

    @Test
    public void test2(){ //更新
        //update(String sql,Object... args)
        int row = jdbcTemplate.update("update u set age = ? where name = ?", 18, "xiaohong");
        System.out.println(row); //1
    }

    @Test
    public void test3(){ //删除
        //update(String sql,Object... args)
        int row = jdbcTemplate.update("delete from u where name = ?", "xiaohong");
        System.out.println(row); //1
    }

    @Test
    public void test4(){ //查询一个
        //queryForObject(String sql , RowMapper<? entends Object> rowMapper , Object... args)
        U u = jdbcTemplate.queryForObject("select * from u where name = ?", new BeanPropertyRowMapper<U>(U.class), "xiaohong");
        System.out.println(u); //U{name='xiaohong', age=18}
    }

    @Test
    public void test5(){ //查询集合
        //queryForObject(String sql , Class<T> requiredType)
        Long count = jdbcTemplate.queryForObject("select count(*) from u", Long.class);
        System.out.println(count); //3
    }

    @Test
    public void test6(){ //查询全部
        //query(String sql , RowMapper<? entends Object> rowMapper)
        List<U> u = jdbcTemplate.query("select * from u", new BeanPropertyRowMapper<U>(U.class));
        System.out.println(u); //[U{name='zhangsan', age=15}, U{name='lisi', age=16}, U{name='xiaohong', age=18}]
    }

}
