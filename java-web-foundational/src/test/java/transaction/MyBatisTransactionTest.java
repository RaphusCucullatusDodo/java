package transaction;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.mapper.AccountMapper;
import cn.raphuscucullatus.java.web.foundational.util.SqlSessionUtil;
//import jdk.swing.interop.SwingInterOpUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * MyBatis事务管理测试用例
 *
 * @author raphus cucullatus
 * @version 2021/9/39:35
 * @since JDK8
 */
public class MyBatisTransactionTest {

    /**
     * 测试事务一般使用(增删改查)
     */
    @Test
    public void testManualTransaction() {
        // 获取SqlSession并且关闭自动开启和提交事务
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = new Account("白骨精", new BigDecimal(2345), 1);
        int insertResult = accountMapper.insert(account);
        System.out.println("获取新增账号的id:" + account.getId());
        Assert.assertEquals(insertResult, 1);
        // 获取SqlSession并且关闭自动开启和提交事务
        SqlSessionUtil.commitAndClose(sqlSession);

    }

    /**
     * 测试自动开启提交事务
     */
    @Test
    public void testAutoCommitTransaction() {
        // 获取SqlSession并且开启自动开启和提交事务
        SqlSession sqlSession = SqlSessionUtil.getSqlSession(true);

        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = new Account("哮天犬", new BigDecimal(100), 1);
        int insertResult = accountMapper.insert(account);
        System.out.println("获取新增账号的id:" + account.getId());
        Assert.assertEquals(insertResult, 1);
    }

    /**
     * 测试 手动事务 以及 回滚
     */
    @Test
    public void testManualTransactionRollback() {
        //关闭 自动开启和提交事务
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

        try {
            List<Account> accountList = accountMapper.selectAccountListByStatusAndName(new Account("白骨精", 1));
            for (Account account : accountList) {
                System.out.println(account.getName() + "转账前资金为:" + account.getBalance());
                account.setBalance(account.getBalance().subtract(new BigDecimal(1000)));
                accountMapper.update(account);
                System.out.println(account.getName() + "转账后资金为:" + account.getBalance());
            }
//            System.out.println(1/0);
            accountList = accountMapper.selectAccountListByStatusAndName(new Account("哮天犬", 1));
            for (Account account : accountList) {
                System.out.println(account.getName() + "转账前资金为:" + account.getBalance());
                account.setBalance(account.getBalance().add(new BigDecimal(1000)));
                accountMapper.update(account);
                System.out.println(account.getName() + "转账后资金为:" + account.getBalance());
            }
//            // 获取SqlSession并且关闭自动开启和提交事务
            SqlSessionUtil.commitAndClose(sqlSession);
        } catch (Exception e) {
            System.out.println("出现异常,事务回滚");
            // 获取SqlSession并且关闭自动开启和提交事务
            SqlSessionUtil.rollbackAndClose(sqlSession);
        }
    }


}
