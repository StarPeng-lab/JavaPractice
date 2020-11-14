package cn.itcast.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //0、强制转换，只有HttpServlet接口中才有关于Http请求消息响应消息的相关方法，而浏览器一般都是基于HTTP协议访问
        HttpServletRequest request = (HttpServletRequest)req; //ServletRequest是HttpServletRequest的父类
        //1、获取资源请求路径
        String requestURI = request.getRequestURI();
        //2、判断是否包含登录相关资源路径，注意css/js/图片/验证码等资源也需要放行
        if(requestURI.contains("/login.jsp") || requestURI.contains("/loginServlet")
             || requestURI.contains("/css/") || requestURI.contains("/js/") || requestURI.contains("/fonts/")
             || requestURI.contains("/checkCodeServlet") ){
            //包含，证明用户此时想登录，放行
            chain.doFilter(req,resp);
        }else{
            //不包含，需要验证用户是否已登录
            //3、从session中获取user键
            Object user = request.getSession().getAttribute("user");
            if(user != null){
                //登录了，放行
                chain.doFilter(req,resp);
            }else{
                //没有登录，跳转登录页面
                request.setAttribute("login_msg","您尚未登录，请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
        //chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
