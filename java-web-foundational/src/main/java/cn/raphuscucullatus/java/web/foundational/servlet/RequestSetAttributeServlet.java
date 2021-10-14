package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * HttpServletRequest作为域对象共享数据
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/3 17:49
 * @since JDK1.8
 */
@WebServlet("/request/set")
public class RequestSetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("courseName", "渡渡鸟学Java");
//        如果不使用请求转发  /request/get  将无法获取此次设置的attribut值
        request.getRequestDispatcher("get").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
