package cn.raphuscucullatus.java.web.foundational.service;

import cn.raphuscucullatus.java.web.foundational.bean.vo.UserVO;
import cn.raphuscucullatus.java.web.foundational.service.impl.UserServiceImpl;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/212:12
 * @since JDK8
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void testLogin() {
        String name = null;
        String password = null;
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        boolean loginResult = userService.login(user);
        System.out.println(loginResult ? "登入成功" : "登入失败");

        name = "admin";
        password = "111111";
        user.setName(name);
        user.setPassword(password);
        loginResult = userService.login(user);
        System.out.println(loginResult ? "登入成功" : "登入失败");
    }

    @Test
    public void testRegister() {
        User user = new User();
        user.setName("Tom");
        user.setPassword("111111");
        boolean register = userService.register(user);
        System.out.println(register ? "注册成功" : "注册失败");
    }

    /**
     * 测试获取所有用户的展示数据
     */
    @Test
    public void testFindAllUser() {
        List<UserVO> allUser = userService.findAllUser();
        for (UserVO user : allUser) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByName() {
        try {
            boolean flag = userService.findByName("asf");
            Assert.assertEquals(flag, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
