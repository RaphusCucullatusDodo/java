package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * HttpServletRequest作为域对象共享数据
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/3 17:55
 * @since JDK1.8
 */
@WebServlet("/request/get")
public class RequestGetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("从request作用域获取courseName的值为：" + (String) request.getAttribute("courseName"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
