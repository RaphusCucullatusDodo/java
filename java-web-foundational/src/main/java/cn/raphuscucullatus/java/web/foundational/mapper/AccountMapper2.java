package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.vo.QueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 账号管理-基于MyBatis实现
 *
 * @author raphus cucullatus
 * @version 2021/8/3015:33
 * @since JDK8
 */
public interface AccountMapper2 {
    /**
     * 通过ID查找账号
     *
     * @param id
     * @return
     */
    Account findById(Long id);


}
