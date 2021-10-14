package cn.raphuscucullatus.java.web.foundational.bean.entity;

import java.util.List;

/**
 * 角色
 *
 * @author raphus cucullatus
 * @version 2021/9/9 19:44
 * @since JDK8
 */
public class Role {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色详情
     */
    private String roleDesc;

    /**
     * 用于封装指定角色的用户
     *
     * @return
     */
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ",userList=" + userList +
                '}';
    }
}
