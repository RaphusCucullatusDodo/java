package cn.raphuscucullatus.java.web.foundational.service.impl;

import cn.raphuscucullatus.java.web.foundational.bean.vo.AccountVO;
import cn.raphuscucullatus.java.web.foundational.bean.vo.PageBean;
import cn.raphuscucullatus.java.web.foundational.dao.AccountDao;
import cn.raphuscucullatus.java.web.foundational.dao.impl.AccountDaoImpl;
import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.exception.BusinessException;
import cn.raphuscucullatus.java.web.foundational.service.AccountService;
import cn.raphuscucullatus.java.web.foundational.util.DruidDataSourceUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * 账户业务逻辑的实现
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2216:08
 * @since JDK8
 */
public class AccountServiceImpl implements AccountService {
    /**
     * Service依赖DAO
     */
    private final AccountDao accountDao = new AccountDaoImpl();


    /**
     * 根据转账金额更新指定账户的余额
     *
     * @param fromAccountName 转账账户
     * @param toAccountName   收款账户
     * @param amount          转账金额
     * @return
     */
    @Override
    public boolean transferAccounts(String fromAccountName, String toAccountName, BigDecimal amount) {

        boolean result = false;
//      使用事务保持业务数据的一致性
        Connection connection = null;

        try {
            connection = DruidDataSourceUtil.getConnection();
//            业务校验
//            业务判断 用户是否存在
            Account fromAccount = accountDao.select(fromAccountName);
            Account toAccount = accountDao.select(toAccountName);
            String transferMassage = "";
            if (null != fromAccount && null != toAccount) {
//                如果转出账户余额大于等于0且大于等于转账金额
                if (fromAccount.getBalance().compareTo(BigDecimal.ZERO) == 1 && fromAccount.getBalance().compareTo(amount) >= 0) {
//                    转账金额大于零
                    if (amount.compareTo(BigDecimal.ZERO) == 1) {
//                  业务校验结束
//                        开始事务（关闭自动提交）
                        connection.setAutoCommit(false);
//                        执行业务操作
//                        更新转账方资金
                        boolean updateResult = accountDao.update(fromAccountName, amount);
                        if (updateResult) {
//                            更新成功则更新收款方资金
                            updateResult = accountDao.update(toAccountName, amount.negate());
                            if (updateResult) {
                                connection.commit();
                                result = true;
                            }
                        }
                    } else {
                        transferMassage = "转出账户余额应大于等于0且大于等于转账金额";
                    }
                } else {
                    transferMassage = "转出账户余额不应小于0或者小于转账金额";
                }
            } else {
                transferMassage = "转出账户或者转入账户不存在";
            }
            if (!"".equals(transferMassage)) {
                throw new RuntimeException(transferMassage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
//              回滚事务
                connection.rollback();

                throw new RuntimeException(e.getMessage());
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean addAccount(Account account) {
        try {
            Account accountCheck = accountDao.selectOne(new Account(account.getName()));
//            查找的账号不存在,则添加账号 否则抛出异常 , 返回 false
            if (accountCheck == null) {
                boolean insertResult = accountDao.insert(account);
                if (insertResult) {
                    return true;
                }
            } else {
                throw new BusinessException("账号名已存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAccountById(Long id) {
        Account accountCheck;
        try {
            accountCheck = accountDao.selectOne(new Account(id));

            if (accountCheck != null) {
                return accountDao.delete(new Account(id));
            } else {
                throw new RuntimeException("删除失败,账号不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean updateAccount(Account account) {
        try {
            Account accountCheck = findAccountById(account.getId());
            if (null != accountCheck) {
                return accountDao.update(account);
            } else {
                throw new RuntimeException("该账号不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Account findAccountById(Long id) {
        try {
            Account account = accountDao.selectOne(new Account(id));
            if (account != null) {
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AccountVO> findAllAccount() throws Exception {
        List<Account> accountList = accountDao.selectAll();
        return accountListToAccountVOList(accountList);
    }

    @Override
    public PageBean<AccountVO> findAccountByPage(Long pageNo, Integer pageSize) {
        PageBean<AccountVO> pageBean = new PageBean<>();
        try {
//            获取总记录条数
            Long totalCount = accountDao.selectAccountTotalCount();
            pageBean.setTotalCount(totalCount);
//            获取分页数据列表
            List<Account> accountList = accountDao.selectAccountByPage(pageNo, pageSize);
            List<AccountVO> accountVOList = accountListToAccountVOList(accountList);
            if (accountVOList != null && accountVOList.size() > 0) {
                pageBean.setDataList(accountVOList);
            }
//            总页数
            pageBean.setTotalPage(totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
//            当前页
            pageBean.setPageNo(pageNo);
//            每页条数
            pageBean.setPageSize(pageSize);
            return pageBean;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<AccountVO> accountListToAccountVOList(List<Account> accountList) {
        List<AccountVO> accountVOList = new ArrayList<>();
        if (null != accountList && accountList.size() > 0) {
            for (Account account : accountList) {
                AccountVO accountVO = new AccountVO(account.getId(), account.getName(), account.getBalance()
                        , account.getStatus(), account.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        , account.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                accountVOList.add(accountVO);
            }
            return accountVOList;
        }
        return null;
    }


}
