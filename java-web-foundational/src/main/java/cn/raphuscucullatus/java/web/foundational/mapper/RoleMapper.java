package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Role;

/**
 * 角色管理-基于Mybatis实现
 *
 * @author raphus cucullatus
 * @version 2021/9/9 21:06
 * @since JDK8
 */
public interface RoleMapper {
    /**
     * 查询指定角色ID的角色信息以及关联的用户列表
     *
     * @param roleId
     * @return
     */
    Role selectRoleUserList(Long roleId);

}
