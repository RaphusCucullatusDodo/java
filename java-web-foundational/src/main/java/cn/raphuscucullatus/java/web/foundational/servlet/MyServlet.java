package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 标准Servlet的创建
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/1 16:33
 * @since JDK1.8
 */
@WebServlet("/my")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理请求
        System.out.println("收到了Get请求");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("<table>" +
                "<tr><td>商品名</td><td>商品价格</td><td>商品产地</td></tr>" +
                "<tr><td>华为mate40pro</td><td>11999</td><td>cn</td></tr>" +
                "<table/>");


    }

    /*<table>
        <tr>
            <td>商品名</td><td>商品价格</td><td>商品产地</td>
        </tr>
        <tr>
            <td>苹果12</td>
            <td>12425</td>
            <td>cn</td>
        </tr>
        <tr>
            <td>小米</td>
            <td>1999</td>
            <td>cn</td>
        </tr>
        <tr>
            <td>华为mate40pro</td>
            <td>11999</td>
            <td>cn</td>
        </tr>

    </table>*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        System.out.println("收到了Post请求");

    }
}
