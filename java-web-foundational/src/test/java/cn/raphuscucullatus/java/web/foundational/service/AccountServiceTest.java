package cn.raphuscucullatus.java.web.foundational.service;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.vo.AccountVO;
import cn.raphuscucullatus.java.web.foundational.bean.vo.PageBean;
import cn.raphuscucullatus.java.web.foundational.service.impl.AccountServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * 转账业务测试类
 *
 * @author raphus cucullatus
 * @version 2021/8/1110:24
 * @since JDK8
 */
public class AccountServiceTest {
    AccountService service = new AccountServiceImpl();

    @Test
    public void testTransferAccount() {
        String formAccountName = "tony";
        String toAccountName = "jack";
        boolean result = service.transferAccounts(formAccountName, toAccountName, new BigDecimal(500));
        System.out.println("业务层转账结果:" + result);
    }

    @Test
    public void testFindAllAccount() throws Exception {
        List<AccountVO> allAccount = service.findAllAccount();
        for (AccountVO accountVO : allAccount) {
            System.out.println(accountVO);
        }

    }

    @Test
    public void testAddAccount() {
        boolean addAccountResult = service.addAccount(new Account("刘翔", new BigDecimal(500000000)));
        Assert.assertEquals(addAccountResult, false);

    }

    @Test
    public void testDelete() {
        boolean deleteResule = service.deleteAccountById(13L);
        Assert.assertEquals(deleteResule, true);
    }

    @Test
    public void testFindAccoutById() {
        Account accountById = service.findAccountById(7L);
        Assert.assertNotNull(accountById);
    }

    @Test
    public void testUpdateAccount() {
        boolean updateAccountResult = service.updateAccount(new Account(7L, "raphus", new BigDecimal(5000000), 0));
        Assert.assertEquals(updateAccountResult, true);
        updateAccountResult = service.updateAccount(new Account(10L, "raphus", new BigDecimal(5000000), 0));

    }

    @Test
    public void testFindAccountByPage() {
        PageBean<AccountVO> pageBean = service.findAccountByPage(2L, 2);
        System.out.println(pageBean);
    }


}

