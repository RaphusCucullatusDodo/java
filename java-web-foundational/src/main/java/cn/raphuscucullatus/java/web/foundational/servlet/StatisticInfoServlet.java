package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 展示网站访问人数
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/1 18:54
 * @since JDK1.8
 */
@WebServlet("/statistic/info")
public class StatisticInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer count = (Integer) getServletContext().getAttribute("count");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("<h1>当前网站访问人数为:" + count + "</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
