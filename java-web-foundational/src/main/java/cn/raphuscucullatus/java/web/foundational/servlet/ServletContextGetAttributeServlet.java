package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/1 18:13
 * @since JDK1.8
 */
@WebServlet("/get")
public class ServletContextGetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object name = getServletContext().getAttribute("name");
        System.out.println("从ServletContext中获取的name属性的值为:" + name);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
