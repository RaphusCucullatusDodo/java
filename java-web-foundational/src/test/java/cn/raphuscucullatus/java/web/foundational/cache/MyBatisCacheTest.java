package cn.raphuscucullatus.java.web.foundational.cache;

import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.mapper.UserMapper;
import cn.raphuscucullatus.java.web.foundational.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.List;

/**
 * MyBatis一级缓存的使用
 *
 * @author raphus cucullatus
 * @version 2021/9/314:27
 * @since JDK8
 */
public class MyBatisCacheTest {

    /**
     * MyBatis一级缓存的使用
     */
    @Test
    public void testMyBatisFirstLevelCacheUsed() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 第一次通过数据库查询 并写入一级缓存
        System.out.println("第一次查询");
        List<User> userList = mapper.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
        // 第二次通过读取一级缓存获取 不查询数据库
        System.out.println("第二次查询");
        userList = mapper.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
        SqlSessionUtil.commitAndClose(sqlSession);
    }

    /**
     * MyBatis一级缓存的使用
     * 不同SqlSession不共享一级缓存
     */
    @Test
    public void testMyBatisFirstLevelCacheUsedDifferentAqlSession() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 通过数据库查询 并写入一级缓存
        System.out.println("1 一个SqlSession第一次查询");
        List<User> userList = mapper.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession = SqlSessionUtil.getSqlSession();
        mapper = sqlSession.getMapper(UserMapper.class);
        // 通过数据库查询 并写入一级缓存
        System.out.println("2 另一个SqlSession第一次查询");
        userList = mapper.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * MyBatis一级缓存的失效
     * 1 调用了SqlSession对象的commit方法 clearCache方法 或者close方法
     * 2 数据发生了增删改
     */
    @Test
    public void testMyBatisFirstLevelCacheUsedInvalid() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 第一次通过数据库查询 并写入一级缓存
        System.out.println("第一次查询");
        List<User> userList = mapper.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
        // 提交数据 缓存失效
//        sqlSession.clearCache();
//        System.out.println("调用commit方法,缓存失效");
        sqlSession.commit();
//        int row = mapper.deleteById(46L);
//        System.out.println(row==1?"删除成功,一级缓存失效":"删除失败,一级缓存也会失效");

        // 第二次 未读取到缓存 同过数据库查询 将查询结果再次写入一级缓存
        System.out.println("第二次查询");
        userList = mapper.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.commit();
    }

    /**
     * 二级缓存的使用
     * 二级缓存可以在同一个SqlSessionFactory下的多个SqlSession指甲剪共享数据
     * 注意:会话关闭后一级缓存才会写入二级缓存
     */
    @Test
    public void testMyBatisSecondLevelCacheUsed() {
        SqlSession sqlSession1 = SqlSessionUtil.getSqlSession();
        SqlSession sqlSession2 = SqlSessionUtil.getSqlSession();
        SqlSession sqlSession3 = SqlSessionUtil.getSqlSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);

        List<User> list1 = userMapper1.selectAll();
        System.out.println("1:" + list1);
//        会话关闭后一级缓存才会写入二级缓存
//        SqlSessionUtil.commitAndClose(sqlSession1);
        sqlSession1.close();
        List<User> list2 = userMapper2.selectAll();
        System.out.println("2:" + list2);
        List<User> list3 = userMapper3.selectAll();
        System.out.println("3:" + list3);
        System.out.println("2==3:" + (list2 == list3));
    }

    /**
     * 二级缓存的失效
     * 1 数据发生了增删改
     */
    @Test
    public void testMyBatisSecondLevelInvalid() {
        SqlSession sqlSession1 = SqlSessionUtil.getSqlSession();
        SqlSession sqlSession2 = SqlSessionUtil.getSqlSession();
        SqlSession sqlSession3 = SqlSessionUtil.getSqlSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        //第一次查询
        System.out.println(userMapper1.selectAll());
//        会话关闭后一级缓存才会写入二级缓存
        System.out.println(userMapper1.deleteById(44L) == 1 ? "删除成功" : "删除失败");
        System.out.println("关闭sqlSession1");
        SqlSessionUtil.commitAndClose(sqlSession1);
        //第二次查询
        System.out.println(userMapper2.selectAll());
        System.out.println("关闭sqlSession2");
        SqlSessionUtil.commitAndClose(sqlSession2);
        //第三次查询
        System.out.println(userMapper3.selectAll());
    }


}
