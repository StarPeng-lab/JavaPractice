package cn.itcast.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 敏感词汇过滤器
 */
@WebFilter("/SensitiveWordsFilter")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1、创建代理对象，增强getParameter方法
        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断是否是String getParameter(String s)方法
                if(method.getName().equals("getParameter")){
                    //增强返回值
                    String value = (String)method.invoke(req,args); //获取返回值
                    if(value!=null){
                        for(String str : list){
                            if(value.contains(str)){
                                value = value.replaceAll(str,"***"); //替换敏感词
                            }
                        }
                    }
                   return value;
                }
                //判断是否是Map<String,String[]> getParameterMap()方法
                if(method.getName().equals("getParameterMap")){
                    //增强返回值
                    Map<String,String[]> map = (Map<String, String[]>) method.invoke(req,args); //获取返回值
                    if(!map.isEmpty()){
                        Set<Map.Entry<String, String[]>> entries = map.entrySet();
                        for(Map.Entry<String,String[]> entry : entries){
                            String key = entry.getKey();
                            String[] values = entry.getValue();
                            for(String str : list){
                                if(values[0].contains(str)){
                                    values[0] = values[0].replaceAll(str,"***"); //替换敏感词
                                    map.put(key,values);
                                }
                            }
                        }
                    }
                    return map;
                }
                //判断是否是String[] getParameterValues(Strings)方法
                //判断是否是Enumeration<String> getParameterNames()方法

                return method.invoke(req,args);
            }
        });
        //放行
        chain.doFilter(proxy_req, resp);
    }

    private List<String> list = new ArrayList<String>(); //敏感词汇集合
    public void init(FilterConfig config) throws ServletException {
        try {
            //1、获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("WEB_INF/classes/敏感词汇.txt"); //项目打包到服务器上时，src下的文件会打包到classes下

            //2、读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));

            //3、将读取到的每一行数据添加到list中
            String line = null;
            while((line=br.readLine())!=null){
                list.add(line);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
