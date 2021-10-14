package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * 我的第一个Servlet，基于注解的方式实现
 *
 * @author raphus cucullatus
 * @version 2021/7/3122:01
 * @since JDK8
 */
@WebServlet(urlPatterns = "/welcome", loadOnStartup = 1, initParams = @WebInitParam(name = "name", value = "Tom"))
public class WelcomeServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("服务器创建WelcomeServlet对象成功");
        String name = servletConfig.getInitParameter("name");
        System.out.println("初始化产参数为" + servletConfig.getInitParameterNames().nextElement() + "=" + name);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("我的第一个Servlet-注解配置实现");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
