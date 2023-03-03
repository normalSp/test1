package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用Service查询
        List<Brand> brands = brandService.selectAll();

        //2.转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3.写数据
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2.调用业务方法
        brandService.add(brand);

        //3.响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接收id数组数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为int类型数组
        int[] ids = JSON.parseObject(params, int[].class);

        //2.调用业务方法
        brandService.deleteByIds(ids);

        //3.响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //1.接受参数 当前页码、每页展示参数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2.调用service查询
        PageBean <Brand> pageBean = brandService.selectByPage(currentPage,pageSize);
        
        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //3.写数据
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接受参数 当前页码、每页展示参数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为brand类型数组
        Brand brand = JSON.parseObject(params, Brand.class);


        //2.调用service查询
        PageBean <Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //3.写数据
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }
}
