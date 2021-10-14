package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.dto.UserInfo;
import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 基于Mybatis实现的用户接口
 *
 * @author raphus cucullatus
 * @version 2021/8/2916:35
 * @since JDK8
 */
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 根据用户名和密码查找用户
     *
     * @param userName
     * @param password
     * @return
     */
    User findUserByUserNamePassword(@Param("name") String userName, @Param("pwd") String password);

    /**
     * 统计用户人数
     *
     * @return
     */
    Long UserTotalCount();

    /**
     * 根据Id查询用户信息
     *
     * @param id
     * @return
     */
    UserInfo selectUserInfoById(Long id);

    /**
     * 根据用户名查询用户信息
     *
     * @param name
     * @return
     */
    Map<String, Object> selectByName(String name);

    /**
     * 根据id删除用户名
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 查询指定用户ID的用户信息以及账户列表
     *
     * @param userId
     * @return
     */
    User selectUserAccountList(Long userId);

    /**
     * 查询指定用户id的用户信息以及关联的角色列表
     *
     * @param userId
     * @return
     */
    User selectUserRoleList(Long userId);

    /**
     * 查找指定id的用户
     *
     * @param userId
     * @return
     */
//    @Select("select user.id, user.name, user.password, user.create_date, user.update_date from jdbc_user user where user.id = #{userId}")
    User selectById(Long userId);

    /**
     * 查询指定用户ID的用户信息以及账户列表(延迟加载)
     *
     * @param userId
     * @return
     */
    @Select("select user.id, user.name, user.password, user.create_date, user.update_date from jdbc_user user where user.id = #{userId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(many = @Many(select = "cn.raphuscucullatus.java.web.foundational.mapper.AccountMapper.selectAccountListByUserId"),
                    column = "id",
                    property = "accountList"
            )
    })
    User selectUserAccountListLazyLoad(Long userId);
}
