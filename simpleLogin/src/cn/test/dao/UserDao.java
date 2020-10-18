package cn.test.dao;

import cn.test.domain.User;
import cn.test.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中User表的类
 */
public class UserDao {
    
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    
    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return 包含用户全部数据，没有查到则返回null
     */
    public User login(User loginUser){
        try {
            //1、编写sql
            String sql = "select * from user where username = ? and password = ?";
            //2、调用query方法
            User user = template.queryForObject(sql,
                     new BeanPropertyRowMapper<User>(User.class),
                     loginUser.getUsername(), loginUser.getPassword());

            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
