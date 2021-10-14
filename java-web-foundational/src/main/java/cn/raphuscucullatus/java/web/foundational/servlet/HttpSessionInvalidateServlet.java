package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 手动销毁Session
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/8 0:48
 * @since JDK1.8
 */
@WebServlet("/session/invalidate")
public class HttpSessionInvalidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        Object courseName = session.getAttribute("courseName");
        session.invalidate();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
