package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Base Servlet
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/17 6:38
 * @since JDK1.8
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        // method参数名和方法名相同 所有方法参数相同 所以不需要逐条对比
        // 用反射获取方法名, 若method参数名和方法名相同则调用该方法
        try {
            Method[] methods = this.getClass().getMethods();
            if (methods != null && !"".equals(method) && methods.length > 0) {
                for (Method currentMethod : methods) {
                    String currentMethodName = currentMethod.getName();
                    if (currentMethodName.equals(method)) {
                        currentMethod.invoke(this, request, response);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
