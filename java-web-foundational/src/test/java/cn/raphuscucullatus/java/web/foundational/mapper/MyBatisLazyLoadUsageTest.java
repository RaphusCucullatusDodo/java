package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

/**
 * 手动实现懒加载
 *
 * @author raphus cucullatus
 * @version 2021/9/10 2:17
 * @since JDK8
 */
public class MyBatisLazyLoadUsageTest {
    @Test
    public void testSManualLazyLoad() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
//        第一次查询
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.selectById(7L);
//        System.out.println("根据账号id查询账户信息"+account);
        //只进行一次查询,只需要获取账号
        System.out.println("根据账号id查询账户信息:" + account.getId() + "," + account.getName() + ","
                + account.getBalance() + "," + account.getStatus());
//        第二次查询
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectById(account.getUserId());
        //进行两次查询,既要获取账号信息 又要获取关联的用户信息
        System.out.println("根据用户id查询用户信息" + user);
    }

    /**
     * 多表之间一对一关系的懒加载
     */
    @Test
    public void testMyBatisAutoLazyLoadOneToOne() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.selectAccountUserInfoLazyLoad(7L);
        //只进行一次查询,只需要获取账号
        System.out.println("根据账号id查询账户信息:" + account.getId() + "," + account.getName() + ","
                + account.getBalance() + "," + account.getStatus());
        // 第二次查询由MyBatis自动调用
        //进行两次查询,既要获取账号信息 又要获取关联的用户信息
        System.out.println("根据账号id查询账号信息以及关联用户信息:" + account);
    }

    /**
     * 多表之间一对多关系的懒加载
     */
    @Test
    public void testMyBatisAutoLazyLoadOneToMore() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserAccountListLazyLoad(1L);
        //只进行一次查询,只需要获取用户信息
//        System.out.println(user.getName()+","+user.getPassword()+","+user.getId()+","+user.getCreateDate()+","+user.getUpdateDate());
        // 第二次查询由MyBatis自动调用
        //进行两次查询,既要获取账号信息 又要获取关联的用户信息
        System.out.println("根据用户ID查询用户信息以及关联账号列表:" + user);
    }


}




