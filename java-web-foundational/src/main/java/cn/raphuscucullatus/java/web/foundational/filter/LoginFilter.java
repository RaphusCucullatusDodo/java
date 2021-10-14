package cn.raphuscucullatus.java.web.foundational.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/12 10:32
 * @since JDK1.8
 */
//@WebFilter("/login")
public class LoginFilter implements Filter {
    /**
     * 通常用于执行一些耗时操作，如读取配置文件
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
//        服务器启动的时候执行一次
        System.out.println("LoginFilter对象的初始化是否在servlet对象创建之前执行执行：是");
    }

    /**
     * doFilter方法，每当与请求资源相匹配时，则执行一次
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        每次请求执行
        System.out.println("LoginFilter拦截所有请求");
//      放行：此次请求通过，让客户端访问其请求资源
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
//        服务器关闭时执行一次
        System.out.println("LoginFilter对象的销毁在servlet对象销毁之后销毁吗：是");
    }
}
