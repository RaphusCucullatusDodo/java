package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * HttpServletResponse设置响应数据
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/5 4:43
 * @since JDK1.8
 */
@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        setResponseLineData(response);
//        setResponseHeaderData(response);
//        sendRedirect(response);
//        response.sendRedirect("https://jd.com");
//        responseText(response);
        responseImages(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 设置响应行数据
     *
     * @param response HttpServletResponse对象
     */
    private void setResponseLineData(HttpServletResponse response) {
//        服务器收到请求但是拒绝处理
//        response.setStatus(403);
//        服务器处理请求时出现异常报错
        response.setStatus(500);
    }

    /**
     * 设置响应头 实现5秒后跳转京东首页
     *
     * @param response HttpServletResponse对象
     */
    private void setResponseHeaderData(HttpServletResponse response) {
//        5秒后跳到京东首页
        response.setHeader("Refresh", "5;url=https://jd.com");
    }

    /**
     * 设置响应头 实现重定向跳转
     *
     * @param response HttpServletResponse对象
     */
    private void sendRedirect(HttpServletResponse response) throws IOException {
//        方式一
//        response.setStatus(302);
//        response.setHeader("Location","https://baidu.com");
//        方式二
        response.sendRedirect("https://jd.com");

    }

    /**
     * 设置响应体 使用字符输出流输出文本字符串
     *
     * @param response HttpServletResponse对象
     */
    private void responseText(HttpServletResponse response) throws IOException {
//        设置MIME类型，以及解码方法（解决中文乱码问题）
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
//        writer() 只能输出字符串 不能输出数字
//        println() 能输出字符串 能输出数字
        writer.write(1);
        writer.write(Integer.toString(1));
        writer.print(1);
    }

    /**
     * 设置响应体 输出一张图片
     *
     * @param response HttpServletResponse对象
     * @throws IOException io异常未处理
     */
    private void responseImages(HttpServletResponse response) throws IOException {
        String file = "images/88ea3ee6de9a9e11.jpg";
        getServletContext().getMimeType(file);
        InputStream resourceAsStream = getServletContext().getResourceAsStream(file);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = resourceAsStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        resourceAsStream.close();
    }
}
