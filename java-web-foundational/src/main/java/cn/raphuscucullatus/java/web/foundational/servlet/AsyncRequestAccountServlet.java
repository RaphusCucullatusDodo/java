package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.response.ResponseBean;
import cn.raphuscucullatus.java.web.foundational.bean.vo.AccountVO;
import cn.raphuscucullatus.java.web.foundational.bean.vo.PageBean;
import cn.raphuscucullatus.java.web.foundational.exception.BusinessException;
import cn.raphuscucullatus.java.web.foundational.service.AccountService;
import cn.raphuscucullatus.java.web.foundational.service.impl.AccountServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 处理客户端账户管理异步请求的Servlet
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/22 8:30
 * @since JDK1.8
 */
@WebServlet("/async/account")
public class AsyncRequestAccountServlet extends BaseServlet {
    private final AccountService accountService = new AccountServiceImpl();


    /**
     * 展示所有账号信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<List<AccountVO>> responseBean = new ResponseBean(Boolean.TRUE);

        try {
            List<AccountVO> accountVOList = accountService.findAllAccount();
            if (null != accountVOList) {
                responseBean.setData(accountVOList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("查询所有账号失败");
        }
        String jsonString = JSON.toJSONString(responseBean);
        System.out.println("【查询所有账号列表】的JSON字符串的结果:" + jsonString);
        //将所有账号类标的JSON字符串写给浏览器
        response.getWriter().write(jsonString);

    }

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
    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<Boolean> responseBean = new ResponseBean<>(Boolean.TRUE);
        String errorMassage;
        Account account;
        //请求参数是JSON时
        if ("application/json;charset=UTF-8".equals(request.getContentType())) {
            account = JSON.parseObject(request.getInputStream(), Account.class);

        } else {
            //请求参数不是JSON时
            String name = request.getParameter("name");
            String balance = request.getParameter("balance");
            String status = request.getParameter("status");
            account = new Account(name, new BigDecimal(balance), Integer.parseInt(status));
        }
        System.out.println(account.toString());
        try {
            errorMassage = validation(account.getName(), account.getBalance().toString(), account.getStatus().toString());
//      若校验通过,通过service添加账户
            if (errorMassage == null) {
                Boolean addAccountResult = accountService.addAccount(account);
                //            若添加成功，跳转到查询所有用户页面
                if (addAccountResult) {
                    responseBean.setData(Boolean.TRUE);
                }
            } else {
                responseBean.setFlag(Boolean.FALSE);
                responseBean.setErrorMessage(errorMassage);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
//            账号已存在,返回添加账号页面并给出提示信息
            responseBean.setErrorMessage(e.getMessage());
            responseBean.setFlag(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setErrorMessage("服务器异常");
            responseBean.setFlag(Boolean.FALSE);
        }

//        将返回数据转换为JSON字符串 并写入浏览器
        String jsonString = JSON.toJSONString(responseBean);
        System.out.println("【异步添加账号】jsonString:" + jsonString);
        response.getWriter().write(jsonString);


    }

    /**
     * 根据id删除账号
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteAccountById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<Boolean> responseBean = new ResponseBean<>(Boolean.TRUE);
        String errorMassage;
        String id = request.getParameter("id");
        if (id != null && !"".equals(id)) {
            try {
                Boolean deleteAccountByIdResult = accountService.deleteAccountById(Long.parseLong(id));
                if (deleteAccountByIdResult) {
                    responseBean.setData(Boolean.TRUE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorMassage = "删除失败";
//            删除失败 并提示失败原因
                responseBean.setErrorMessage(errorMassage);
                responseBean.setFlag(false);
            }
        } else {
            errorMassage = "id不能为空";
//            删除失败 并提示失败原因
            responseBean.setErrorMessage(errorMassage);
            responseBean.setFlag(false);
        }
        //        将返回数据转换为JSON字符串 并写入浏览器
        String jsonString = JSON.toJSONString(responseBean);
        response.getWriter().write(jsonString);
        System.out.println("【异步删除账号】jsonString:" + jsonString);

    }

    /**
     * 会显账号数据
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void toAccountUpdatePage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        ResponseBean<Account> responseBean = new ResponseBean<>(Boolean.TRUE);

        try {
            Account account = accountService.findAccountById(Long.parseLong(id));
            if (null != account) {
                responseBean.setData(account);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("回显账号失败");
        }

        //转化为JSON并响应给客户端
        String jsonString = JSON.toJSONString(responseBean);
        response.getWriter().write(jsonString);
//        System.out.println("【回显数据toAccountUpdatePage方法的JSON字符串:】"+jsonString);
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
        ResponseBean<Boolean> responseBean = new ResponseBean<>(Boolean.TRUE);
        String errorMassage;
        Account account;
        //请求参数是JSON时
        if ("application/json;charset=UTF-8".equals(request.getContentType())) {
            account = JSON.parseObject(request.getInputStream(), Account.class);
        } else {
            //请求参数不是JSON时
            //        获取post请求参数
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String balance = request.getParameter("balance");
            String status = request.getParameter("status");
            account = new Account(Long.parseLong(id), name, new BigDecimal(balance), Integer.parseInt(status));
        }
        System.out.println(account.toString());
        try {
            errorMassage = validation(account.getName(), account.getBalance().toString(), account.getStatus().toString());

//      若校验通过,没有错误信息 则通过service修改账户信息
            if (errorMassage == null) {

                Boolean updateAccountResult = accountService.updateAccount(account);
//                 修改成功
                if (updateAccountResult) {
                    responseBean.setData(Boolean.TRUE);
                }
            } else {
//                    校验失败
                responseBean.setFlag(Boolean.FALSE);
                responseBean.setErrorMessage(errorMassage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("修改账号失败");
        }

        //转化为JSON字符串 并响应给客户端
        String jsonString = JSON.toJSONString(responseBean);
        response.getWriter().write(jsonString);
        System.out.println("【修改账户数据accountUpdate方法的JSON字符串:】" + jsonString);


    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     */
    public void findAccountByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseBean<PageBean<AccountVO>> responseBean = new ResponseBean<>(Boolean.TRUE);

        try {
//        获取页码
            Long pageNo = Long.valueOf(request.getParameter("pageNo"));
//        获取每页条数
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));

            PageBean<AccountVO> pageBean = accountService.findAccountByPage(pageNo, pageSize);

            if (null != pageBean && null != pageBean.getDataList() && pageBean.getDataList().size() > 0) {
                responseBean.setData(pageBean);
            } else {
                responseBean.setFlag(Boolean.FALSE);
                responseBean.setErrorMessage("没有查询到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("分页查询账号异常");
        }

        //转化为JSON字符串 并响应给客户端
        String jsonString = JSON.toJSONString(responseBean);
        response.getWriter().write(jsonString);
        System.out.println("【分页查询账号信息】的JSON字符串" + jsonString);

    }


}

