package cn.raphuscucullatus.java.web.foundational.service;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.bean.vo.AccountVO;
import cn.raphuscucullatus.java.web.foundational.bean.vo.PageBean;
import cn.raphuscucullatus.java.web.foundational.bean.vo.QueryVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户业务逻辑
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2216:04
 * @since JDK8
 */
public interface AccountService {

    /**
     * 转账业务逻辑
     *
     * @param fromAccount 转账账户
     * @param toAccount   收款账户
     * @param amount      转账金额
     * @return 转账是否成功
     */
    boolean transferAccounts(String fromAccount, String toAccount, BigDecimal amount);

    /**
     * 查询所有账户
     *
     * @return
     * @throws Exception
     */
    List<AccountVO> findAllAccount() throws Exception;

    /**
     * 添加账号
     *
     * @param account
     * @return
     */
    boolean addAccount(Account account);

    /**
     * 删除账号
     *
     * @param id
     * @return
     */
    boolean deleteAccountById(Long id);

    /**
     * 查询账号
     *
     * @param id
     * @return
     */
    Account findAccountById(Long id);

    /**
     * 修改账号
     *
     * @param account
     * @return
     */
    boolean updateAccount(Account account);

    /**
     * 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageBean<AccountVO> findAccountByPage(Long pageNo, Integer pageSize);


}

