package cn.raphuscucullatus.java.web.foundational.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/13 4:08
 * @since JDK1.8
 */
@WebFilter(value = "/request/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class DispatchTypesFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("DispatchTypesFilter过滤器拦截到了" + httpServletRequest.getRequestURL() + "的请求");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
