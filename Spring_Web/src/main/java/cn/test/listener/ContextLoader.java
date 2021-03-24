package cn.test.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation"); //读取在web.xml的全局参数

        ApplicationContext app = new ClassPathXmlApplicationContext(contextConfigLocation); //通过在web.xml中配置全局参数，再用servletContext加载参数，达到applicationContext.xml文件名更改时，只需修改配置文件web.xml即可

        servletContext.setAttribute("app",app); //将Spring的应用上下文对象存到servletContext域中
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
