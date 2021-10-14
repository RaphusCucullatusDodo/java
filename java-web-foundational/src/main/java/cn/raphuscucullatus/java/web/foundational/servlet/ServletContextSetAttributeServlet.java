package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/1 18:07
 * @since JDK1.8
 */
@WebServlet("/set")
public class ServletContextSetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().setAttribute("name", "Tom");
        System.out.println("将name属性的值存入ServletContext");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
