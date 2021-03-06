package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.deleteUser(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        //1、遍历数组
        for(String id : ids){
            //2、调用dao删除
            dao.deleteUser(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0 ){
            currentPage = 1;
        }

        //1、创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2、设置参数
        pb.setRows(rows);

        //3、调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        //4、计算总页码
        int totalpage = (totalCount%rows) == 0 ? totalCount/rows : totalCount/rows+1;
        pb.setTotalPage(totalpage);

        if(currentPage >= totalpage){
            currentPage = totalpage;
        }
        pb.setCurrentPage(currentPage); //2、设置参数

        //5、调用dao查询List集合
        int start = (currentPage-1)*rows; //计算开始的索引记录
        List<User> list= dao.findByPage(start,rows,condition);
        pb.setList(list);


        return pb;
    }
}
