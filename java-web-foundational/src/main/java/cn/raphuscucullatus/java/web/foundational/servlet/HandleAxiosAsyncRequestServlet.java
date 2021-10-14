package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.bean.request.UserLoginRequest;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/24 11:01
 * @since JDK1.8
 */
@WebServlet("/axios")
public class HandleAxiosAsyncRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("获取Content-Type:" + request.getContentType());
        String name;
        String password;
        UserLoginRequest userLoginRequest;
        //按照Content-Type处理请求参数
        //获取Content-Type:application/json;charset=UTF-8
        if ("application/json;charset=UTF-8".equals(request.getContentType())) {
            userLoginRequest = JSON.parseObject(request.getInputStream(), UserLoginRequest.class);
            name = userLoginRequest.getName();
            password = userLoginRequest.getPassword();
        } else {
            System.out.println("获取Content-Type不是json");
            name = request.getParameter("name");
            password = request.getParameter("password");
            //设置响应给客户端的内容类型,默认为null
            userLoginRequest = new UserLoginRequest(name, password);
        }
        System.out.println("请求参数是name:" + name + ",password:" + password);
        response.setContentType("text/html");
        String responseJSONSting = JSON.toJSONString(userLoginRequest);
        response.getWriter().write(responseJSONSting);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
