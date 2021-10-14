package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.dto.UserInfo;
import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 基于MyBatis的增删改查
 *
 * @author raphus cucullatus
 * @version 2021/8/2917:02
 * @since JDK8
 */
public class UserMapperTest {
    @Test
    public void testUserSelectAll() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.selectAll();
        System.out.println("获取所有用户信息:" + userList);
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testFindUserByUserNamePassword() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findUserByUserNamePassword("admin", "111111");
        System.out.println("获取所有用户信息:" + user);

        SqlSessionUtil.commitAndClose(sqlSession);
    }

    @Test
    public void testUserTotalCount() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Long count = mapper.UserTotalCount();
        System.out.println("总人数为:" + count);
    }

    @Test
    public void testUserSelectById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserInfo userInfo = mapper.selectUserInfoById(1L);
        System.out.println("根据id获取用户信息" + userInfo);
    }

    @Test
    public void testUserSelectByName() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> accountName = mapper.selectByName("渡渡鸟");
        System.out.println("查询结果为:" + accountName);
        System.out.println("渡渡鸟的密码是:" + accountName.get("password"));
    }

    @Test
    public void testSelectUserAccountList() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserAccountList(1L);
        System.out.println("用户信息以及账号列表为:" + user);
    }

    @Test
    public void selectUserRoleList() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserRoleList(2L);
        System.out.println("用户信息以及角色列表为:" + user);
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectById(1L);
        System.out.println("用户的信息为:" + user);

    }


}
