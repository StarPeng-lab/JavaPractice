package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库

        //定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try{
            String sql = "select * from user where username=? and password=?";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
            return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        //1、定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        //2、执行sql
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getAddress());
    }

    @Override
    public void deleteUser(int id) {
        String sql = "delete from user where id= ?";
        template.update(sql,id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void updateUser(User user) {
        String sql ="update user set name=? , gender=? , age=? , address=? , qq=? , email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1、定义模版初始化sql
        String sql = "select count(*) from user where 1=1";

        StringBuilder sb = new StringBuilder(sql);
        //2、遍历map，根据对应的key是否有值来拼接sql
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for(String key : keySet){

            //排除分页条件参数currentPage和rows
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            String value = condition.get(key)[0]; //获取第一个值即可
            if(value != null && !"".equals(value)){
                sb.append(" and "+key+" like ? "); //有值，将条件凭借到sb中，现在sb即为sql
                params.add("%"+value+"%"); //?条件的值
            }
        }

        return template.queryForObject(sb.toString(),Integer.class,params.toArray()); //返回一个Integer类型的数据，会自动拆箱为int（1.5新特性）
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1=1";

        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for(String key : keySet){

            //排除分页条件参数currentPage和rows
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value!=null || !"".equals(value)){
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");
            }
        }

        sb.append(" limit ?,? "); //添加分页查询
        params.add(start);
        params.add(rows); //添加分页查询参数值

        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }

}
