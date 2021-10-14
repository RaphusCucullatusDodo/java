package cn.raphuscucullatus.java.web.foundational.dao;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * 账户的数据访问接口
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2121:37
 * @since JDK8
 */
public interface AccountDao {


    /**
     * 插入一条记录
     *
     * @return
     * @throws SQLException
     */
    boolean insert(Account account) throws SQLException;

    /**
     * 根据条件删除账号信息
     *
     * @param accountCondition 条件
     * @return 删除记录条数
     * @throws SQLException sql
     */
    boolean delete(Account accountCondition) throws SQLException;

    /**
     * 根据转账金额更新指定账户的余额
     *
     * @param accountName 指定账户
     * @param amount      转账金额
     * @return 是否成功
     * @throws SQLException sql
     */
    boolean update(String accountName, BigDecimal amount) throws SQLException;

    /**
     * 修改账号
     *
     * @param account
     * @return
     * @throws SQLException
     */
    boolean update(Account account) throws SQLException;

    /**
     * 查询账号的总数
     *
     * @return
     */
    Long selectAccountTotalCount() throws SQLException;

    /**
     * 根据条件查询账号信息
     *
     * @param accountCondition 条件
     * @return 返回Account对象
     * @throws Exception
     */
    Account selectOne(Account accountCondition) throws Exception;

    /**
     * 根据用户名查询账号信息
     *
     * @param accountName 指定账户
     * @return 返回账号信息
     * @throws Exception sql
     */

    Account select(String accountName) throws Exception;

    /**
     * 查询所有账号
     *
     * @return
     * @throws Exception
     */
    List<Account> selectAll() throws Exception;

    /**
     * 分页查询账号信息
     *
     * @param pageNo   当前页码
     * @param pageSize 每页记录数
     * @return
     * @throws Exception
     */
    List<Account> selectAccountByPage(Long pageNo, Integer pageSize) throws Exception;

    /**
     * 按条件 分页查询账号信息
     *
     * @param pageNo
     * @param pageSize
     * @param accountCondition
     * @return
     * @throws Exception
     */
    List<Account> selectAccountByPage(Long pageNo, Integer pageSize, Account accountCondition) throws Exception;


}
