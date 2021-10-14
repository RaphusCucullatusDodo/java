package cn.raphuscucullatus.java.web.foundational.bean.vo;

import java.math.BigDecimal;


/**
 * Account View Object
 *
 * @author raphus cucullatus
 * @version 2021/8/146:38
 * @since JDK8
 */
public class AccountVO {
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
    private String createDate;
    /**
     * 更新日期
     */
    private String updateDate;

    public AccountVO() {
    }

    public AccountVO(long id) {
        this.id = id;
    }

    public AccountVO(String name) {
        this.name = name;
    }

    public AccountVO(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public AccountVO(String name, BigDecimal balance, Integer status) {
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public AccountVO(Long id, String name, BigDecimal balance, Integer status) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public AccountVO(Long id, String name, BigDecimal balance, Integer status, String createDate, String updateDate) {
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "AccountVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
