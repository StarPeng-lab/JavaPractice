package cn.itcast.web.Servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/checkUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //1、获取前台输入的用户名
        String username = request.getParameter("username");

        //2、创建msg存储校验信息
        Map<String,Object> msg = new HashMap<>();

        //3、校验数据库是否已存在此添加的用户名,调用service的findAll()查找所有用户
        UserService service = new UserServiceImpl();
        List<User> users= service.findAll();

        boolean flag = true;
        for(User u : users){
            if(username != null && !"".equals(username)){
                if(username.equals(u.getName())){
                    //添加的用户名在数据库已存在
                    msg.put("userExist",true); //以map形式存储数据，与json形式一致
                    msg.put("msg","此用户名太受欢迎，请更换一个");
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            //不存在
            msg.put("userExist",false);
            msg.put("msg","");
        }

        //4、将map转为json，并传给客户端（浏览器）
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),msg);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
