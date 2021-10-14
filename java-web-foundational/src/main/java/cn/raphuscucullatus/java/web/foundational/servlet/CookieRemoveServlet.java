package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.util.CookieConfig;
import cn.raphuscucullatus.java.web.foundational.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/7 2:44
 * @since JDK1.8
 */
@WebServlet("/cookie/remove")
public class CookieRemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        目标；删除CookieName=courseName的Cookie
//        服务器给浏览器写指定名称的Cookie 且路径为当前项目的
//        String name = "courseName";
//        String value = "Java";
//        Cookie cookie = new Cookie(name,value);
//        cookie.setPath(request.getContextPath());
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
        String name = request.getParameter("cookieName");
        if (null != name) {
            CookieConfig cookieConfig = new CookieConfig();
            cookieConfig.setCookieName(name);
            cookieConfig.setPath(request.getContextPath());
            CookieUtil.delCookie(response, cookieConfig);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
