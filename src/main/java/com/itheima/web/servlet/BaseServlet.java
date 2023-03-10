package com.itheima.web.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//替换HttpServlet，根据最后一段路径进行方法分发
public class BaseServlet extends HttpServlet {

    //根据请求的最后一段路径进行分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径
        String uri = req.getRequestURI();//uri = "brand-case/brand/selectAll"
        //2.获取最后一段路径--方法名
        int index = uri.lastIndexOf("/");
        String methodName = uri.substring(index + 1);

        //3.执行方法
        //2.1 获取BrandServlet/UserServlet 字节码对象Class
        //之后BrandServlet或UserServlet被调用时，因为他们继承了本类，所以this会指向BrandServlet或UserServlet本身
        Class<? extends BaseServlet> cls = this.getClass();

        //2.2 获取方法Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //2.3 执行对象
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
