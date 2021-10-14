package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.vo.AccountVO;
import cn.raphuscucullatus.java.web.foundational.bean.vo.PageBean;
import cn.raphuscucullatus.java.web.foundational.service.AccountService;
import cn.raphuscucullatus.java.web.foundational.service.impl.AccountServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 2 查询所有账号
 * 3 添加账号
 * 4 删除账号
 * 5 修改账号
 * 6 分页查询
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/14 13:26
 * @since JDK1.8
 */
@WebServlet("/account")
public class AccountServlet extends BaseServlet {
    AccountService accountService = new AccountServiceImpl();
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if ("findAllAccount".equals(method)) {
//            findAllAccount(request, response);
//        } else if ("addAccount".equals(method)) {
//            addAccount(request, response);
//        } else if ("deleteAccountById".equals(method)) {
//            deleteAccountById(request, response);
//        } else if ("toAccountUpdatePage".equals(method)) {
//            toAccountUpdatePage(request, response);
//        } else if ("accountUpdate".equals(method)) {
//            accountUpdate(request, response);
//        } else if ("findAccountByPage".equals(method)) {
//            findAccountByPage(request, response);
//        }
//    }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }

    /**
     * 校验前端数据
     *
     * @param name
     * @param balance
     * @param status
     * @return
     */
    private String validation(String name, String balance, String status) {
        String errorMassage = null;
        if (null == name || "".equals(name)) {
            errorMassage = "账户名不能为空";
        } else if (null == status || "".equals(status)) {
            errorMassage = "账户状态不能为空";
        } else if (null == balance || "".equals(balance)) {
            errorMassage = "账户余额不能为空";
        } else if (new BigDecimal(balance).compareTo(BigDecimal.ZERO) == -1) {
            errorMassage = "账户余额应不小于零";
        }
        return errorMassage;
    }

    /**
     * 添加账号
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String balance = request.getParameter("balance");
        String status = request.getParameter("status");

        String errorMassage;
        try {
            errorMassage = validation(name, balance, status);
//      若校验通过,通过service添加账户
            if (errorMassage == null) {
                Boolean addAccountResult = accountService.addAccount(new Account(name, new BigDecimal(balance), Integer.parseInt(status)));
                //            若添加成功，跳转到查询所有用户页面
                if (addAccountResult) {
                    //                重定向: 客户端发送一个新的请求(服务器响应的)
                    response.sendRedirect("account?method=findAllAccount");
                }
            } else {
                //   校验未通过,将errorMassage存入域对象
                request.setAttribute("errorMassage", errorMassage);
                // 并回到登入页面，并显示内容错误信息               请求转发实在服务器内部进行,浏览器网址不变
                request.getRequestDispatcher("pages/account/account_add.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            账号已存在,
            errorMassage = e.getMessage();
            request.setAttribute("errorMassage", errorMassage);
            //  返回账号添加页面,并给出提示信息      请求转发实在服务器内部进行,浏览器网址不变
            request.getRequestDispatcher("pages/account/account_add.jsp").forward(request, response);
        }

    }

    /**
     * 根据id删除账号
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteAccountById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMassage;
        try {
            String id = request.getParameter("id");
            Boolean deleteAccountByIdResult = accountService.deleteAccountById(Long.parseLong(id));
            if (deleteAccountByIdResult) {
//                删除成功跳转到显示有有账号页面
                response.sendRedirect("account?method=findAllAccount");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMassage = e.getMessage();
//            删除失败 显示所有账号列表页面,并提示失败原因
            request.setAttribute("errorMassage", errorMassage);
            request.getRequestDispatcher("account?method=findAllAccount").forward(request, response);
        }
    }

    /**
     * 跳转到修改账号页面 回显账号信息
     *
     * @param request
     * @param response
     */
    public void toAccountUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Account account = accountService.findAccountById(id);
        if (null != account) {
            request.setAttribute("account", account);
            request.getRequestDispatcher("pages/account/account_update.jsp").forward(request, response);
        } else {
//            没有查询到就回到查询所有账号页面
            request.getRequestDispatcher("account?method=findAllAccount").forward(request, response);
        }


    }

    /**
     * 修改账号信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void accountUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取post请求参数
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String balance = request.getParameter("balance");
        String status = request.getParameter("status");

        String errorMassage;
        try {
            errorMassage = validation(name, balance, status);
//      若校验通过,没有错误信息 则通过service修改账户信息
            if (errorMassage == null) {

                Boolean updateAccountResult = accountService.updateAccount(new Account(Long.parseLong(id), name, new BigDecimal(balance), Integer.parseInt(status)));

                if (updateAccountResult) {
                    //                重定向: 使客户端发送一个新的请求(服务器响应的)
                    response.sendRedirect("account?method=findAllAccount");
//      若修改失败，跳转到查询所有用户页面
                } else {
                    //   校验未通过,将errorMassage存入域对象
                    request.setAttribute("errorMassage", errorMassage);
                    // 并回到修改页面，并显示内容错误信息      请求转发实在服务器内部进行,浏览器网址不变${pageContext.request.contextPath}/account?method=toAccountUpdatePage&id=id
                    request.getRequestDispatcher("account?method=findAllAccount").forward(request, response);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//    若查询发生异常  则获取修改账号的异常信息
            errorMassage = e.getMessage();
            // 将errorMassage存入域对象
            request.setAttribute("errorMassage", errorMassage);
            // 回到修改页面，并显示内容错误信息      请求转发实在服务器内部进行,浏览器网址不变  account?method=toAccountUpdatePage&id=id
            request.getRequestDispatcher("account?method=toAccountUpdatePage&id=" + id).forward(request, response);

        }

    }

    /**
     * 展示所有账号信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<AccountVO> allAccount = accountService.findAllAccount();
            if (null != allAccount) {
//             将accountVO存入合适的域对象内
                request.setAttribute("accountList", allAccount);
//            请求转发跳转到account_list.jsp
                request.getRequestDispatcher("pages/account/account_list.jsp").forward(request, response);
            } else {
//            没有查询到数据则返回首页
                request.getRequestDispatcher("index.html").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            遇到异常之后返回首页
            request.getRequestDispatcher("index.html").forward(request, response);
        }

    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     */
    public void findAccountByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        获取页码
        Long pageNo = Long.valueOf(request.getParameter("pageNo"));
//        获取每页条数
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        PageBean<AccountVO> pageBean = accountService.findAccountByPage(pageNo, pageSize);
        if (null != pageBean && null != pageBean.getDataList() && pageBean.getDataList().size() > 0) {
            request.setAttribute("pageBean", pageBean);
//            跳转到展示页面account_list_limit.jsp
            request.getRequestDispatcher("pages/account/account_list_limit.jsp").forward(request, response);
        }


    }

    /**
     * 用户转账业务
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void accountTransfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //       获取接收请求参数
        String fromAccountName = request.getParameter("fromAccountName");
        String toAccountName = request.getParameter("toAccountName");
        String amount = request.getParameter("amount");
//        校验请求数据
        String transferMassage = validationTransfer(fromAccountName, toAccountName, amount);
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
        request.getRequestDispatcher("pages/transfer.jsp").forward(request, response);
    }

    /**
     * 校验前端转账数据
     *
     * @param fromAccountName
     * @param toAccountName
     * @param amount
     * @return
     */
    private String validationTransfer(String fromAccountName, String toAccountName, String amount) {
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


}
