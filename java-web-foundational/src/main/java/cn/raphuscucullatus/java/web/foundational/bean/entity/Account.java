package cn.raphuscucullatus.java.web.foundational.bean.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2121:27
 * @since JDK8
 */
public class Account {
    /**
     * 账户id
     */
    private Long id;
    /**
     * 账户名
     */
    private String name;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 状态
     * 1 启用
     * 0 禁用
     */
    private Integer status;
    /**
     * 创建日期
     */
    private LocalDateTime createDate;
    /**
     * 更新日期
     */
    private LocalDateTime updateDate;

    /**
     * 用于封装Account表与User表关联查询时User表的信息
     */
    private User user;

    /**
     * 该账户的用户Id
     */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Account() {
    }

    public Account(Integer status) {
        this.status = status;
    }

    public Account(Long id) {
        this.id = id;
    }

    public Account(String name) {
        this.name = name;
    }

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    public Account(String name, BigDecimal balance, Integer status) {
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public Account(Long id, String name, BigDecimal balance, Integer status) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public Account(Long id, String name, BigDecimal balance, Integer status, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ",user=" + user +
                ",userId=" + userId +
                '}';
    }
}
