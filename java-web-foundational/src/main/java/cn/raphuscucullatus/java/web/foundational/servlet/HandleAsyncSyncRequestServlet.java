package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 处理客户端的同步和异步请求
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/19 21:39
 * @since JDK1.8
 */
@WebServlet("/handle")
public class HandleAsyncSyncRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        System.out.println("HandleAsyncSyncRequestServlet接收到了请求," +
                "请求的参数为: name = " + name + "请求的方式: " + request.getMethod());
//        System.out.println(1/0);
        response.setContentType("text/html");
        response.getWriter().write("响应数据" + name);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
