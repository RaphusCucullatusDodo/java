package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 设置Session对象属性值
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/7 22:54
 * @since JDK1.8
 */
@WebServlet("/session/set")
public class HttpSessionSetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("courseName", "Java");
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
//        由于闲置30分钟后服务器就会自动删除Session，所以大于1800秒没有意义
        cookie.setMaxAge(1800);
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
