package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 统计网站人数
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/1 18:23
 * @since JDK1.8
 */
//@WebServlet(value = "*.html",loadOnStartup = 1)
public class StatisticServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        Integer count = 0;
        getServletContext().setAttribute("count", count);
        System.out.println("服务器启动之后网站访问的人数是:" + count);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("客户端的请求地址是:" + request.getRequestURL());
        System.out.println("客户端的user agent是:" + request.getHeader("user-agent"));
        Integer count = (Integer) getServletContext().getAttribute("count");
        count++;
        getServletContext().setAttribute("count", count);
        System.out.println("客户端访问之后当前访问人数为" + count);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
