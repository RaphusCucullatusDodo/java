package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/2 22:01
 * @since JDK1.8
 */
@WebServlet("/forward")
public class ForwardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        完整的URL  http://localhost:8080/javaweb/forward
//        项目路径   http://localhost:8080/javaweb
//        服务器路径 http://localhost:8080

//        目标路径 http://localhost:8080/javaweb/pages/forward.html
//        绝对路径 (以/开头的路径 对请求转发而言在在完整URL基础之上省略项目路径,对于非请求转发而言在完整的URL基础之上省略服务器路径)
        request.getRequestDispatcher("/pages/forward.html").forward(request, response);

//        当前路径  http://localhost:8080/javaweb/forward
//        目标路径  http://localhost:8080/javaweb/pages/forward.html
//        相对路径 (以/开头的路径 是以目标资源相对于当前资源路径)
//        request.getRequestDispatcher("pages/forward.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
