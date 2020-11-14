package cn.itcast.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * 敏感词汇过滤器
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1、创建代理对象，增强getParameter方法
        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //a.判断是否是String getParameter(String s)方法
                if(method.getName().equals("getParameter")){
                    //增强返回值
                    String value = (String)method.invoke(req,args); //获取返回值
                    if(value != null){
                        for(String str : list){
                            if(value.contains(str)){
                                value = value.replaceAll(str,"***"); //替换敏感词
                            }
                        }
                    }
                    return value;
                }

                //b.判断是否是Map<String,String[]> getParameterMap()方法
                //方法一：new一个新的map
                if(method.getName().equals("getParameterMap")){
                    //增强返回值
                    Map<String,String[]> values =new HashMap((Map<String,String[]>)method.invoke(req,args)); //获取返回值
                    if(values.size() > 0){
                        for(String key : values.keySet()){
                            String[] value = values.get(key);
                            for(String str : list){
                                if(value[0].contains(str)){ //只取String[]的第一个值
                                    value[0] = value[0].replaceAll(str,"***");
                                    values.put(key,value);
                                }
                            }
                        }
                    }
                    return values;
                }

                //b.方法二：重写getParameterMap()，重新new一个map
                /*if(method.getName().equals("getParameterMap")){

                    // 重写HttpServletRequestWrapper的getParameterMap()
                    HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) req){
                        @Override
                        public Map<String,String[]> getParameterMap(){
                            Map<String,String[]> newMap = new HashMap<>();
                            newMap.putAll(super.getParameterMap());// Map<String,String[]> map = (Map<String, String[]>) method.invoke(req,args); //获取返回值
                            if(!newMap.isEmpty()){
                                Set<Map.Entry<String, String[]>> entries = newMap.entrySet();
                                for(Map.Entry<String,String[]> entry : entries){
                                    String key = entry.getKey();
                                    String[] values = entry.getValue();
                                    for(String str : list){
                                        if(values[0].contains(str)){ //只取String[]参数中的第一个值
                                            values[0] = values[0].replaceAll(str,"***"); //替换敏感词
                                            newMap.put(key,values); //直接对原数据进行更改会抛出 java.lang.IllegalStateException: 不允许修改锁定的参数映射
                                        }
                                    }
                                }
                            }

                            return Collections.unmodifiableMap(newMap);
                        }
                    };

                    //增强返回值
                    Map<String, String[]> parameterMap = requestWrapper.getParameterMap(); //调用匿名类中的方法
                    return parameterMap; // 最后需要返回这个方法的返回值
                }*/

                //c.判断是否是String[] getParameterValues(Strings)方法
                //d.判断是否是Enumeration<String> getParameterNames()方法

                return method.invoke(req,args);
            }
        });

        //2、放行
        chain.doFilter(proxy_req, resp);
    }

    // 在一开始就加载 【敏感词汇.txt】文件
    private List<String> list = new ArrayList<String>(); //敏感词汇集合
    public void init(FilterConfig config) throws ServletException {
        try {
            //1、获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/敏感词汇.txt"); //项目打包到服务器上时，src下的文件会打包到classes下

            //2、读取文件，由于txt是UTF-8的编码，而服务器不是，因此需要设置读取的编码方式
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(realPath),"UTF-8"));

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