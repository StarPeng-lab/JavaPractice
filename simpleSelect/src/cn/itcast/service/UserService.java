package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 验证登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 保存新增对象
     * @param user
     */
    public void addUser(User user);

    /**
     * 根据id删除User
     * @param id
     */
    public void deleteUser(String id);

    /**
     * 根据id查询，返回一个User对象
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 更新用户
     * @param user
     */
    public void updateUser(User user);

    /**
     * list.jsp中，批量删除选中用户
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     *分页查询 + 条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
