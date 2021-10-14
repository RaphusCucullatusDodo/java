package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.service.UserService;
import cn.raphuscucullatus.java.web.foundational.service.impl.UserServiceImpl;
import cn.raphuscucullatus.java.web.foundational.util.CookieConfig;
import cn.raphuscucullatus.java.web.foundational.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/6 22:53
 * @since JDK1.8
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * Web层依赖Service层
     */
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("收到客户端请求");
//        处理Post请求乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
//        获取请求参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
//        获取客户端输入验证ma
        String clientCaptche = request.getParameter("captcha");
//        获取服务器生成验证码
//        Session的应用场景，将Servlet生成的验证码保存在Session中
        String serverCaptcha = (String) request.getSession().getAttribute("captcha");
        String errorMessage = null;
        if (serverCaptcha.equalsIgnoreCase(clientCaptche)) {
//        校验请求参数
            if (null != name && null != password && !"".equals(name) && !"".equals(password)) {
                User user = new User();
                user.setName(name);
                user.setPassword(password);
                boolean login = userService.login(user);
                if (login) {
                    String remember = request.getParameter("remember");
//                  登入成功后，如果勾选了记住密码 则保存至cookie 否则删除cookie
                    if (null != remember) {
                        CookieConfig cookieConfig = new CookieConfig();
                        cookieConfig.setCookieName("name");
                        cookieConfig.setCookieValue(user.getName());
                        cookieConfig.setPath(request.getContextPath());
                        cookieConfig.setMaxAge(15 * 24 * 60 * 60);
                        CookieUtil.addCookie(response, cookieConfig);
                        cookieConfig.setCookieName("password");
                        cookieConfig.setPath(request.getContextPath());
                        cookieConfig.setCookieValue(user.getPassword());
                        CookieUtil.addCookie(response, cookieConfig);
                    } else {
//                        没有勾选则删除账号密码Cookie
                        CookieConfig cookieConfig = new CookieConfig();
                        cookieConfig.setCookieName("name");
                        cookieConfig.setCookieValue(user.getName());
                        cookieConfig.setPath(request.getContextPath());

                        CookieUtil.delCookie(response, cookieConfig);
                        cookieConfig.setCookieName("password");
                        cookieConfig.setCookieValue(user.getPassword());
                        cookieConfig.setPath(request.getContextPath());

                        CookieUtil.delCookie(response, cookieConfig);
                    }

                    //将用户数据传入session中
                    request.getSession().setAttribute("user", user);
                    //登入成功后跳转到success.jsp并且显示登入信息
                    response.sendRedirect("pages/success.jsp");
                } else {
                    errorMessage = "用户名或密码错误";
//              选择 request  context  session
                    //request 因为每次登录都要访问Servlet，而且错误信息每次不一定相同，传给其他其他页面后销毁即可
                    request.setAttribute("errorMassage", errorMessage);
//                    跳转到login.jsp并显示错误信息
                    request.getRequestDispatcher("pages/Login.jsp").forward(request, response);
                }
            } else {
                errorMessage = "用户名和密码不能为空";
                request.setAttribute("errorMassage", errorMessage);
                request.getRequestDispatcher("pages/Login.jsp").forward(request, response);
            }
        } else {
            errorMessage = "请输入正确的验证码";
            request.setAttribute("errorMassage", errorMessage);
            request.getRequestDispatcher("pages/Login.jsp").forward(request, response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
