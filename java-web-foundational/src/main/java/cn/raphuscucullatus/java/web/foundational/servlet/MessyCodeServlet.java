package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * POST请求和POST请求乱码处理
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/5 17:03
 * @since JDK1.8
 */
@WebServlet("/messy")
public class MessyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("客户端请求方式:" + request.getMethod());
//        处理POST请求乱码
        request.setCharacterEncoding("utf-8");
        final String name = request.getParameter("name");
        System.out.println("请求参数为name的值为：" + name);
//        处理POST响应的乱码
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html");
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("<h1>渡渡鸟学Java<h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
