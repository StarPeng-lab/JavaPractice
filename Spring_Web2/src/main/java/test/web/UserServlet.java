package test.web;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import test.service.UserService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext(); //获得servletContext对象
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);//获得servletContext域中的 Spring上下文对象ApplicationContext
        UserService userService = (UserService) app.getBean("service");
        userService.show();
    }
}

