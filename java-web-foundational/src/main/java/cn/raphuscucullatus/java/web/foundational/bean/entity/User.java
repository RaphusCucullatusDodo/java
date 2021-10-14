package cn.raphuscucullatus.java.web.foundational.bean.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户实体
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/209:38
 * @since JDK8
 */
//实现序列化接口 是启用二级缓存的前提
public class User implements Serializable {
    /**
     * id
     */
    @JsonIgnore
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建日期
     */
    /*   将字段名 createDate 转化为 createDateTime*/
    @JsonProperty("createDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * 用于封装用户所拥有的账号
     */
    private List<Account> accountList;

    /**
     * 用于封装用户所对应的角色
     */
    private List<Role> roleList;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(Integer id, String name, String password, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("createDateTime")
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserMapper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ",accountList=" + accountList +
                ",roleList=" + roleList +
                '}';
    }
}
