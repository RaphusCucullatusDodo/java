package cn.raphuscucullatus.java.web.foundational.dao;

import cn.raphuscucullatus.java.web.foundational.dao.impl.AccountDaoImpl;
import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import com.alibaba.druid.stat.JdbcResultSetStat;
import com.mysql.cj.util.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * 账户的数据访问测试类
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/223:28
 * @since JDK8
 */
public class AccountDaoTest {
    AccountDao accountDao = new AccountDaoImpl();

    /**
     * 测试更新指定用户数据的方法
     *
     * @throws SQLException
     */
    @Test
    public void testUpdateNew() throws SQLException {
        boolean transferResult = accountDao.update("tony", new BigDecimal(500));
        System.out.println("数据是否更新成功：" + transferResult);

    }

    @Test
    public void testSelect() {
        try {
            List<Account> accountList = accountDao.selectAll();
            Assert.assertEquals(accountList.size(), 7);
            if (null != accountList) {
                for (Account account : accountList) {
                    System.out.println(account);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        boolean insertResult = false;
        try {
            insertResult = accountDao.insert(new Account("黄渤", new BigDecimal(10000000)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(insertResult, true);
    }

    @Test
    public void testSelectOne() {
        try {
            Account raphus = accountDao.selectOne(new Account("raphus"));
            Assert.assertEquals(raphus.getName(), "raphus");
            Account 啊哈 = accountDao.selectOne(new Account("啊哈"));

            Assert.assertEquals(啊哈, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() throws SQLException {
        boolean deleteResult = accountDao.delete(new Account(14L));
        Assert.assertEquals(deleteResult, true);
    }

    @Test
    public void testSelectOneById() {
        try {
            Account account = accountDao.selectOne(new Account(7l));
            Assert.assertNotNull(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateAccount() {
        try {
            boolean updateResult = accountDao.update(new Account(7L, "raphuscucullatus", new BigDecimal(5000000), 0));
            Assert.assertEquals(updateResult, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAccountTotalCount() throws SQLException {
        Long totalCount = accountDao.selectAccountTotalCount();
        Assert.assertEquals(totalCount.longValue(), 8);
    }

    @Test
    public void testSelectAccountByPage() {
        try {
            List<Account> accountList = accountDao.selectAccountByPage(1L, 10);
//            Assert.assertEquals(accountList.size(),2);
//            遍历列表数据
            for (Account account : accountList) {
                System.out.println(account);
            }
            System.out.println(accountList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAccountByPageCondition() {
        try {
            List<Account> accountList = accountDao.selectAccountByPage(1L, 10, new Account(1));
//            Assert.assertEquals(accountList.size(),3);
//            遍历列表数据
            int i = 6;
            for (Account account : accountList) {
                System.out.print(accountList.size() - (i--) + ":");
                System.out.println(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
