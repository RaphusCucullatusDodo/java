package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.bean.vo.UserVO;
import cn.raphuscucullatus.java.web.foundational.service.UserService;
import cn.raphuscucullatus.java.web.foundational.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/10 10:46
 * @since JDK1.8
 */
@WebServlet("/user/list")
public class ShowUserListServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        调用所有用户展示数据的方法
        List<UserVO> userVOList = userService.findAllUser();
        if (null != userService) {
//            通过请求转发将数据转发到jstl.jsp
            request.setAttribute("userVOList", userVOList);
            request.getRequestDispatcher("/pages/user_list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
