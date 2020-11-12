package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

/**
 * 用户操作的DAO
 */
public interface UserDao {
    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username,String password);
    public void addUser(User user);
    public void deleteUser(int id);
    User findUserById(int id);
    public void updateUser(User user);
}
