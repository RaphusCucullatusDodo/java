package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.bean.response.ResponseBean;
import cn.raphuscucullatus.java.web.foundational.exception.BusinessException;
import cn.raphuscucullatus.java.web.foundational.service.UserService;
import cn.raphuscucullatus.java.web.foundational.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/21 10:58
 * @since JDK1.8
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {

    UserService service = new UserServiceImpl();

    public void checkName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<Boolean> responseBean = new ResponseBean<>(Boolean.TRUE);

        try {
            String name = request.getParameter("name");
            String errorMessage = validation(name);
            if (errorMessage == null) {
//                int i =1/0;
                boolean isExists = service.findByName(name);
                //用户名不存在
                if (!isExists) {
                    //
                    responseBean.setData(true);
                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("服务器异常");
        }
//        将展示在客户端的数据(Java对象)转换为JSON字符串
        String responseJSONString = JSON.toJSONString(responseBean);
        System.out.println("【客户端异步请求校验用户名】服务端响应给客户端的JSON字符转:" + responseJSONString);
        response.getWriter().write(responseJSONString);
    }

    /**
     * 校验用户名
     *
     * @param name
     * @return
     */
    public String validation(String name) {
        String errorMessage = null;
        if (null == name && "".equals(name)) {
            errorMessage = "用户名不能为空";
        }
        return errorMessage;
    }
}
