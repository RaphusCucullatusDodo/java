package cn.raphuscucullatus.java.web.foundational.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version ${DATE} ${TIME}
 * @since JDK1.8
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    public ContextLoaderListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        System.out.println("ServletContextListener监听服务器启动了，项目部署路径为：" + sce.getServletContext().getContextPath());
//        Spring初始化IOC容器
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        System.out.println("ServletContextListener监听服务器关闭了，项目部署路径为：" + sce.getServletContext().getContextPath());

    }


}
