package cn.raphuscucullatus.java.web.foundational.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/13 4:37
 * @since JDK1.8
 */
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("characterEncodingFilter拦截了所有请求");
//        处理POST请求乱码
        request.setCharacterEncoding("UTF-8");
//        处理POST请求接响应乱码
        response.setCharacterEncoding("UTF-8");
//      放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
