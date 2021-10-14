package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.service.UserService;
import cn.raphuscucullatus.java.web.foundational.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

/**
 * 注册用户servlet
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/6 20:13
 * @since JDK1.8
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        处理Post请求中文乱码(获取请求数据前设置)
        request.setCharacterEncoding("utf-8");
//        处理响应Post请求中文乱码(响应客户端前设置)
        response.setContentType("text/html;charset=utf-8");

//        使用commons-beanutils 升级改造 获取请求参数、参数校验部分
        //获取所有参数
        Map<String, String[]> allParameterMap = request.getParameterMap();
        if (null != allParameterMap && 0 < allParameterMap.size()) {
            User user = new User();
            try {
                // 给 所有参数值 赋值给 JavaBean对应的属性 map的key必须和对象的属性名一致
                BeanUtils.populate(user, allParameterMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean register = userService.register(user);
            response.getWriter().write(register ? "<h1>注册成功<h1>" : "<h1>注册失败<h1>");
        } else {
            response.getWriter().write("<h1>注册失败<h1>");
        }


////        获取请求参数
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//
////        请求参数校验
//        if (null!=name&&!("".equals(name))&&null!=password&&!"".equals(password)){
//            UserMapper user = new UserMapper();
//            user.setPassword(password);
//            user.setName(name);
//
//            boolean register = userService.register(user);
//
//            response.getWriter().write("<h1>注册成功<h1>");
//        }else {
//            response.getWriter().write("<h1>注册失败<h1>");
//
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
