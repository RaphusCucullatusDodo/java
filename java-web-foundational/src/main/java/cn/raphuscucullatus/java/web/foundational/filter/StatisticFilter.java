package cn.raphuscucullatus.java.web.foundational.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 统计网站访问人数的Filter
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/13 23:34
 * @since JDK1.8
 */
@WebFilter("/*")
public class StatisticFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        Integer count = 0;
        config.getServletContext().setAttribute("count", count);
        System.out.println("服务器启动后，访问网站的人数为：" + count);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("StatisticFilter拦截到可客户端请求。");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        客户端的请求地址为
        System.out.println("客户端的请求地址的URI为:" + httpServletRequest.getRequestURI());
        System.out.println("客户端的user agent:" + httpServletRequest.getRequestURI());
        Integer count = (Integer) httpServletRequest.getServletContext().getAttribute("count");
        httpServletRequest.getServletContext().setAttribute("count", count + 1);
        System.out.println("当前访问网站的人数为:" + httpServletRequest.getServletContext().getAttribute("count"));
//        放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
