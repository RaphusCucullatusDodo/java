package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * 我的第一个Servlet-XML配置实现
 *
 * @author raphus cucullatus
 * @version 2021/7/3120:32
 * @since JDK8
 */
public class HelloWorldServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("1 服务器启动后：服务器创建helloworld对象成功");
//        通过servletConfig对象获取servlet初始化参数
        String contextConfigLocation = servletConfig.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation的值为:" + contextConfigLocation);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("2 客户端发起请求：我的第一个Servlet-XML配置实现");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("3 Tomcat服务器停止服务；销毁Servlet对象");
    }
}
