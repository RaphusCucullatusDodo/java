package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.dao.impl.AccountDaoImpl;
import cn.raphuscucullatus.java.web.foundational.service.AccountService;
import cn.raphuscucullatus.java.web.foundational.service.impl.AccountServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 用户转账
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/10 20:01
 * @since JDK1.8
 */
@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    /**
     * Servlet依赖Service
     */
    AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        transfer(request, response);

    }

    public void transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //       获取接收请求参数
        String fromAccountName = request.getParameter("fromAccountName");
        String toAccountName = request.getParameter("toAccountName");
        String amount = request.getParameter("amount");
//        校验请求数据
        String transferMassage = validation(fromAccountName, toAccountName, amount);
        if (null == transferMassage) {
            try {
                boolean transferResult = accountService.transferAccounts(fromAccountName, toAccountName, new BigDecimal(amount));
                transferMassage = transferResult ? "转账成功" : "转账失败";
            } catch (Exception e) {
                e.printStackTrace();
                transferMassage = e.getMessage();
            }

        }
        request.setAttribute("transferMassage", transferMassage);
        request.getRequestDispatcher("/pages/transfer.jsp").forward(request, response);
    }

    private String validation(String fromAccountName, String toAccountName, String amount) {
        String result = null;
        if (null == fromAccountName || "".equals(fromAccountName)) {
            result = "转账账号不能为空";
        }
        if (null == toAccountName || "".equals(toAccountName)) {
            result = "转入账号不能为空";
        }
        if (null == amount || "".equals(amount) || Double.parseDouble(amount) < 0) {
            result = "转账金额不能为空或者小于0";
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
