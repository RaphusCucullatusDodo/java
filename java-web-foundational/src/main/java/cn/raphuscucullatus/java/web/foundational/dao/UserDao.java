package cn.raphuscucullatus.java.web.foundational.dao;

import cn.raphuscucullatus.java.web.foundational.bean.entity.User;

import java.util.List;

/**
 * 用户数据访问(对数据的增删改查)接口
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/209:35
 * @since JDK8
 */
public interface UserDao {


    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 根据id或者用户名删除用户
     *
     * @param userCondition
     * @return
     */
    int delete(User userCondition);

    /**
     * 根据id或者用户名更改用户名
     *
     * @param userCondition
     * @return
     */
    int update(User userCondition);

    /**
     * 查询用户
     *
     * @param userCondition
     * @return
     */
    List<User> select(User userCondition);

    long count();
}
