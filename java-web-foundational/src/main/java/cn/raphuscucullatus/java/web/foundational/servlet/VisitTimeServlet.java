package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.util.CookieConfig;
import cn.raphuscucullatus.java.web.foundational.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/7 12:13
 * @since JDK1.8
 */
@WebServlet("/visit")
public class VisitTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
//        第一次访问，则响应第一次访问；否则，相应上次访问时间
        String cookieName = "visitTime";
        Cookie cookie = CookieUtil.getCookie(request, cookieName);
//        cookie为空则为第一次访问，根据情况设置响应内容
        String responseText = cookie == null ? "<h1>第一次访问网站<h1>" : "<h1>上次访问时间" + cookie.getValue() + "<h1>";
        response.getWriter().write(responseText);
//        更新cookie
        CookieConfig cookieConfig = new CookieConfig();
        cookieConfig.setPath(request.getContextPath());
        cookieConfig.setCookieName(cookieName);
//        cookies不能有空格 一般不保存中文
        cookieConfig.setCookieValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hh:mm:ss")));
        CookieUtil.addCookie(response, cookieConfig);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
