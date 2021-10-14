package cn.raphuscucullatus.java.web.foundational.service;

import cn.raphuscucullatus.java.web.foundational.bean.vo.UserVO;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;

import java.util.List;

/**
 * 用户业务逻辑
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/211:47
 * @since JDK8
 */
public interface UserService {
    /**
     * 根据用户名和密码登入
     *
     * @param user
     * @return
     */
    public boolean login(User user);

    /**
     * 实现用户注册（需要用户名和密码）
     *
     * @param user
     * @return
     */
    public boolean register(User user);

    /**
     * 实现查询用户信息 (不带分页)
     *
     * @return
     */
    public List<UserVO> findAllUser();

    /**
     * 根据用户名查找用户
     *
     * @return false:用户名不存在 (可用)
     */
    boolean findByName(String name);
}
