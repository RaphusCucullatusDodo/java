package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.vo.QueryVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 账号管理-基于MyBatis实现
 *
 * @author raphus cucullatus
 * @version 2021/8/3015:33
 * @since JDK8
 */
public interface AccountMapper {
    /**
     * 通过ID查找账号
     *
     * @param id
     * @return
     */
//    @Select("select user.id, user.name, user.password, user.create_date, user.update_date from jdbc_user user where user.id = #{id}")
    Account selectById(Long id);

    /**
     * 增加账号
     *
     * @param account
     * @return
     */
//    @Insert("insert into jdbc_account values (null,#{name},#{balance},#{status},now(),now(),#{userId})")
//    @SelectKey(resultType=Long.class,before=false,keyProperty="id",keyColumn="id",statement = "select last_insert_id()")
    int insert(Account account);

    /**
     * 修改账号
     *
     * @param account
     * @return
     */
//    @Update("update jdbc_account set name = #{name}, balance=#{balance}, status=#{status}, update_date=now() where id = #{id}")
    int update(Account account);

    /**
     * 根据Id删除账号
     *
     * @param id
     * @return
     */
//    @Delete("delete from jdbc_account where id = #{id}")
    int deleteById(Long id);


    /**
     * 分页查询账号列表
     *
     * @param map
     * @return
     */
    List<Account> selectAccountByPage(Map<String, Long> map);

    /**
     * 分页查询(条件QueryVO)
     *
     * @param queryVO
     * @return
     */
    List<Account> selectAccountByQueryVO(QueryVO queryVO);

    /**
     * 根据账号状态查询账号
     *
     * @param status
     * @return
     */
    List<Account> selectAccountListByStatus(Integer status);

    /**
     * 根据账号状态和姓名查询账号
     *
     * @param account
     * @return
     */
    List<Account> selectAccountListByStatusAndName(Account account);

//    /**
//     * 根据id删除账号
//     * @param ids
//     * @return
//     */
//    int deleteByIds (List<Long> ids);

    /**
     * 指定id删除(多个)账号
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") Long... ids);

    /**
     * 根据指定的账号id获取账号信息以及关联的用户信息
     *
     * @param accountId
     * @return
     */
    Account selectAccountUserInfo(Long accountId);

    /**
     * 根据指定的账号id获取账号信息以及关联的用户信息 (延迟加载)
     *
     * @param accountId
     * @return
     */
//    @Select("select id, name, balance, status, create_date, update_date, user_id from jdbc_account where id = #{accountId}")
//    @Results({
//            @Result(property = "userId",column = "user_id",id = true),
//            @Result(property = "user",column = "user_id"
//                    ,one = @One(select = "cn.raphuscucullatus.java.web.foundational.mapper.UserMapper.selectById")
//            )
//    })
    Account selectAccountUserInfoLazyLoad(Long accountId);

    /**
     * 根据指定的用户id获取账号信息以及关联的用户信息
     *
     * @param userId
     * @return
     */
    @Select("select id, name, balance, status, create_date, update_date, user_id from jdbc_account where user_id = #{userId}")
    List<Account> selectAccountListByUserId(Long userId);


}
