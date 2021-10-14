package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.util.CookieConfig;
import cn.raphuscucullatus.java.web.foundational.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/7 2:13
 * @since JDK1.8
 */
@WebServlet("/cookie/set")
public class CookieSetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        服务器给浏览器写入指定名称的Cookie，一般不储存中文
//        String name = "courseName";
//        String value = "Java";
//        Cookie cookie = new Cookie(name,value);
////        配置Cookie 设置cookie有效期（单位：s）从设置时间开始计算 默认为-1（关闭浏览器后自动删除）
////        设置保存时间为7天
//        cookie.setMaxAge(7*24*60*60);
////        设置Cookie有效路径  当前项目路径
//        cookie.setPath(request.getContextPath());
////        通过设置响应头的方式将Cookie写入浏览器
//        response.addCookie(cookie);

        CookieConfig cookieConfig = new CookieConfig();
        cookieConfig.setCookieName("courseName");
        cookieConfig.setCookieValue("Java");
        cookieConfig.setPath(request.getContextPath());
        CookieUtil.addCookie(response, cookieConfig);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
