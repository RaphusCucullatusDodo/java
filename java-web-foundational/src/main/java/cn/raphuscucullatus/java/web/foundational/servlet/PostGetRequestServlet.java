package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * HttpServletRequest获取请求数据
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/2 18:13
 * @since JDK1.8
 */
@WebServlet("/request")
public class PostGetRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getRequestLineData(request);
//        getRequestHeaderData(request);
//        getRequestBodyData(request);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * HttpServletRequest获取请求体数据
     *
     * @param request HttpServletRequest对象
     */
    private void getRequestBodyData(HttpServletRequest request) {
//        Get请求没有请求体只能获取请求参数
//        客户端测试访问地址:http://localhost:8080/javaweb/request?name=raphus&password=88888888
//        参数只有一个值
        System.out.println("获取指定参数名对应值:" + request.getParameter("name")
                + "获取指定参数名对应值:" + request.getParameter("password"));
//      参数有多个值    http://localhost:8080/javaweb/request?name=raphus&password=88888888&hobby=travel&hobby=game
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("获取指定参数的值" + Arrays.toString(hobbies));
//        获取所有参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            System.out.print("参数名为:" + entry.getKey());
            System.out.println("参数值为:" + Arrays.toString(entry.getValue()));
        }
    }

    /**
     * HttpServletRequest获取请求头数据
     *
     * @param request HttpServletRequest对象
     */
    private void getRequestHeaderData(HttpServletRequest request) {
        System.out.println(request.getHeader("UserMapper-Agent"));
        //获取所有请求头名
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            System.out.print("请求头名:" + nextElement);
            System.out.println(",获取相应请求头数据:" + request.getHeader(nextElement));
        }
    }

    /**
     * HttpServletRequest获取请求行数据
     *
     * @param request HttpServletRequest对象
     */
    private void getRequestLineData(HttpServletRequest request) {
        System.out.println("获取客户端发起请求方式" + request.getMethod());
        System.out.println("获取请求参数（get请求URL？后的参数）" + request.getQueryString());
//        请求的URL：http://localhost:8080/javaweb/request
//        URL 表示统一资源定位符,在互联网中访问该资源的地址
        System.out.println("获取请求的URL" + request.getRequestURL());
//        请求的URI：javaweb/request 由项目名称（虚拟路径）+资源名称组成
//        URI表示统一资源标识符，在当前服务器中可以使用这个地址来唯一标标识这个资源
        System.out.println("获取请求的URI" + request.getRequestURI());
        System.out.println("获取服务器的端口" + request.getServerPort());
        System.out.println("获取请求的协议信息" + request.getProtocol());
        System.out.println("获取请求的访问的项目名（虚拟路径）" + request.getContextPath());
        System.out.println("获取请求（客户端）的IP" + request.getRemoteAddr());
    }
}
