package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Role;
import cn.raphuscucullatus.java.web.foundational.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

/**
 * 角色管理的测试
 *
 * @author raphus cucullatus
 * @version 2021/9/9 21:31
 * @since JDK8
 */
public class RoleMapperTest {
    @Test
    public void testSelectRoleUserList() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        Role role = roleMapper.selectRoleUserList(5L);
        System.out.println("指定角色ID的角色信息以及关联的用户列表:" + role);
    }
}
