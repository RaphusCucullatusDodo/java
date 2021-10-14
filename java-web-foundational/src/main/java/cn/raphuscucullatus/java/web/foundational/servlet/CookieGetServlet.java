package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.util.CookieConfig;
import cn.raphuscucullatus.java.web.foundational.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Cookie实现会话的数据共享
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/7 2:14
 * @since JDK1.8
 */
@WebServlet("/cookie/get")
public class CookieGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        获取指定cookie
//        //先获取所有cookie
//        Cookie[] cookies = request.getCookies();
//        if (null!=cookies&&0<cookies.length) {
//            for (Cookie cookie : cookies) {
//                if ("courseName".equals(cookie.getName())) {
//                    System.out.println(cookie.getName()+":"+cookie.getValue()
//                            +",最大年龄为："+cookie.getMaxAge()+",路径为："+cookie.getPath());
//                }
//            }
//        }
        Cookie course = CookieUtil.getCookie(request, "courseName");
        if (null != course) {
            System.out.println("获取Cookie为courseName的值为" + course.getValue());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
