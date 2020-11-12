package cn.itcast.web.Servlet;

import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectServlet")
public class DelSelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取所有的id
        String[] ids = request.getParameterValues("uid");//获取list.jsp页面-复选框中传来的所有用户id
        //2、调用service删除
        UserService service = new UserServiceImpl();
        service.delSelectedUser(ids);
        //3、跳转到list.jsp，查询所有的Servlet，（没有共享数据，因此用response重定向即可）
        //request.getRequestDispatcher()
        response.sendRedirect(request.getContextPath()+"/userListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
