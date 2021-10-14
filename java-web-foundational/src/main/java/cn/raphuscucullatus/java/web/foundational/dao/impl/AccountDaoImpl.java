package cn.raphuscucullatus.java.web.foundational.dao.impl;

import cn.raphuscucullatus.java.web.foundational.dao.AccountDao;
import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.util.CustomQueryRunner;
import cn.raphuscucullatus.java.web.foundational.util.DruidDataSourceUtil;
//import com.sun.source.tree.AssertTree;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

/**
 * 账户的数据访问接口的实现
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2122:13
 * @since JDK8
 */
public class AccountDaoImpl implements AccountDao {
    /**
     * 根据指定数据源创建QueryRunner对象
     */
    private final CustomQueryRunner queryRunner = new CustomQueryRunner(DruidDataSourceUtil.getDataSource());

    @Override
    public boolean insert(Account account) throws SQLException {
        String sql = "insert into jdbc_account values (null,?,?,?,now(),now())";
        int row = queryRunner.update(sql, account.getName(), account.getBalance(), account.getStatus());
        return row == 1;
    }

    @Override
    public boolean delete(Account accountCondition) throws SQLException {
        int row;
        if (null != accountCondition && null != accountCondition.getId()) {
            String sql = "delete from jdbc_account where id = ?";
            row = queryRunner.update(sql, accountCondition.getId());
            return row == 1;
        }
        return false;
    }

    /**
     * 根据转账金额更新指定账户的余额
     *
     * @param accountName 指定账户
     * @param amount      转账金额
     * @return 数据库受影响行数
     * @throws SQLException sql
     */
    @Override
    public boolean update(String accountName, BigDecimal amount) throws SQLException {
        String sql = "update jdbc_account set balance = balance - ? , update_date = now() where name = ?";
        Connection connection = DruidDataSourceUtil.getConnection();
        int row = queryRunner.update(connection, sql, amount, accountName);
        return row == 1;
    }

    @Override
    public boolean update(Account account) throws SQLException {
        String sql = "update jdbc_account set name = ? ,balance = ? ,status = ? ,update_date=now() where id = ?";
        int row = queryRunner.update(sql, account.getName(), account.getBalance(), account.getStatus(), account.getId());
        return row == 1 ? true : false;
    }

    /**
     * 根据用户名查询账号信息
     *
     * @param accountName 指定账户
     * @return 返回账号信息
     * @throws SQLException sql
     */
    @Override
    public Account select(String accountName) throws SQLException {
        String sql = "select * from jdbc_account where name = ?";
        Account account = queryRunner.queryForObject(sql, Account.class, accountName);
        return account == null ? null : account;
    }

    @Override
    public Account selectOne(Account accountCondition) throws Exception {
        Account account = null;
        if (null != accountCondition && null != accountCondition.getName() && !"".equals(accountCondition.getName())) {
            String sql = "select id,name,balance,status,create_date,update_date from jdbc_account where name = ?";
            account = queryRunner.queryForObject(sql, Account.class, accountCondition.getName());
        } else if (null != accountCondition && null != accountCondition.getId() && !"".equals(accountCondition.getId())) {
            String sql = "select id,name,balance,status,create_date,update_date from jdbc_account where id = ?";
            account = queryRunner.queryForObject(sql, Account.class, accountCondition.getId());
        }
        return null == account ? null : account;
    }

    @Override
    public List<Account> selectAll() throws Exception {
        String sql = "select id,name,balance,status,create_date,update_date from jdbc_account";
        List<Account> accountList = queryRunner.queryForList(sql, Account.class);

        return (null != accountList && accountList.size() > 0) ? accountList : null;
    }

    @Override
    public Long selectAccountTotalCount() {
        String sql = "select count(*) from jdbc_account";
        Long totalCount = queryRunner.queryForType(sql, Long.class);
        if (null != totalCount) {
            return totalCount;
        }
        return null;
    }

    @Override
    public List<Account> selectAccountByPage(Long pageNo, Integer pageSize) throws Exception {
        String sql = "select id,name,balance,status,create_date,update_date from jdbc_account limit ?,?";
        //视频此处有异常抛出
        List<Account> accountList = queryRunner.queryForList(sql, Account.class
                , (pageNo - 1) * pageSize, pageSize);
        if (null != accountList) {
            return accountList;
        }
        return null;
    }

    @Override
    public List<Account> selectAccountByPage(Long pageNo, Integer pageSize, Account accountCondition) throws Exception {
        String sql = null;
        if (null != accountCondition && null != accountCondition.getStatus()) {
            sql = "select id,name,balance,status,create_date,update_date from jdbc_account where status=? limit ?,?";
        } else if (null != accountCondition && null != accountCondition.getName()) {
            sql = "select id,name,balance,status,create_date,update_date from jdbc_account where name=? limit ?,?";
        } else if (null != accountCondition && null != accountCondition.getBalance()) {
            sql = "select id,name,balance,status,create_date,update_date from jdbc_account where balance=? limit ?,?";
        } else if (null != accountCondition && null != accountCondition.getId()) {
            sql = "select id,name,balance,status,create_date,update_date from jdbc_account where id=? limit ?,?";
        }
        List<Account> accountList = null;
        if (sql != null) {
            accountList = queryRunner.queryForList(sql, Account.class
                    , accountCondition.getStatus(), (pageNo - 1) * pageSize, pageSize);
            if (null != accountList) {
                return accountList;
            }
        }
        return null;
    }

}
