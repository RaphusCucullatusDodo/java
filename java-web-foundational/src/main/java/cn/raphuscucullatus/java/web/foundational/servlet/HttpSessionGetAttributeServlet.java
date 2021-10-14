package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 获取Session对象属性
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/7 23:07
 * @since JDK1.8
 */
@WebServlet("/session/get")
public class HttpSessionGetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String courseName = (String) session.getAttribute("courseName");
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("<h1>从Session域对象获取属性值为：" + courseName + "<h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
