package cn.raphuscucullatus.java.web.foundational.dao;

import cn.raphuscucullatus.java.web.foundational.dao.impl.CustomQueryRunnerUserDaoImpl;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
//import com.sun.xml.internal.bind.v2.model.core.ID;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.temporal.ChronoUnit;
import java.util.List;


/**
 * 用户数据访问接口测试
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2010:49
 * @since JDK8
 */
public class UserDaoTest {
    //    private UserDao userDao = new StatementUserDaoImpl();
//    private UserDao userDao = new PreparedStatementUserDaoImpl();
    private UserDao userDao = new CustomQueryRunnerUserDaoImpl();

    @BeforeClass
    public void getUserDaoImpl() {
        System.out.println("当前UserDao实现类为：" + userDao.getClass());
    }

    /**
     * 测试新增用户
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("raphus");
        user.setPassword("666666");
        int row = userDao.insert(user);
        System.out.println(row == 1 ? "新增用户成功" : "新增用户失败");
    }

    @Test
    public void testDelete() {

        User userCondition = new User();
        userCondition.setId(15);
        int row = userDao.delete(userCondition);
        System.out.println(row == 1 ? "根据id删除用户成功" : "根据id删除用户失败");

        User user = new User();
        user.setName("jack");
        user.setPassword("666666");
        int delete = userDao.delete(user);
        System.out.println(delete == 1 ? "根据用户名删除用户成功" : "根据用户名删除用户失败");
    }

    @Test
    public void testUpdate() {
        User userCondition = new User();
        userCondition.setId(2);
        userCondition.setName("tony_CTO");
        int row = userDao.update(userCondition);
        System.out.println(row == 1 ? "根据修id改用户成功" : "根据id修改用户失败");

        User user = new User();
        user.setPassword("111111");
        user.setName("tony_CTO");
        int userRow = userDao.update(user);
        System.out.println(userRow == 1 ? "根据修姓名改用户成功" : "根据姓名修改用户失败");

    }

    /**
     * 1.查询所有用户
     * 2.根据ID查询单个用户
     * 3.根据用户名和密码查询单个用户(登入业务)
     */
    @Test
    public void testSelect() {
        User user = new User();

        List<User> users = userDao.select(null);
        System.out.println("查询所有用户: " + users);
        System.out.println("**************************分割线****************************");

        user.setId(1);
        /* List<UserMapper>*/
        users = userDao.select(user);
        System.out.println("根据ID查询单个用户: " + users);
        System.out.println("**************************分割线****************************");

        user.setId(null);
        user.setName("tony_CTO");
        user.setPassword("111111");
        users = userDao.select(user);
        System.out.println("根据用户名和密码查询单个用户: " + users);

        System.out.println("**************************分割线****************************");
        user = new User();
        user.setName("asmin");
        user.setPassword("111'or'1=1");
        List<User> select = userDao.select(user);
        System.out.println("sql注入后的查询结果:" + select);

    }

    @Test
    public void testSelectByName() {
        List<User> userList = userDao.select(new User("admin"));
        Assert.assertEquals(userList.size(), 1);
    }


    @Test
    public void testSelectCount() {
        System.out.println("当前用户数量是" + userDao.count());
    }


}
