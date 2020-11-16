package cn.itcast.web.Servlet;

import cn.itcast.domain.Province;
import cn.itcast.service.ProvinceService;
import cn.itcast.service.impl.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用service查询
        ProvinceService service = new ProvinceServiceImpl();
        List<Province> list = service.findAll();
        //2、序列化list为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        //3、响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json); //直接访问provinceServlet的话数据会显示在页面上
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
