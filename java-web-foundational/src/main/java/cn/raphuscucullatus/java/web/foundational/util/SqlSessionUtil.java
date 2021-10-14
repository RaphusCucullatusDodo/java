package cn.raphuscucullatus.java.web.foundational.util;

import cn.raphuscucullatus.java.web.foundational.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.persistence.SecondaryTable;
import java.io.IOException;
import java.io.InputStream;

/**
 * SqlSession工具类
 *
 * @author raphus cucullatus
 * @version 2021/8/3021:00
 * @since JDK8
 */
public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 静态代码块初始化sqlSessionFactory
     * 因为整个项目只能有一个sqlSessionFactory
     */
    static {
        try (
                InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        ) {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sqlSession
     * 此方法会关闭自动开启事务和自动提交处理事务
     *
     * @return
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 获取sqlSession
     * 此方法可设置 是否自动开启事务和自动提交处理事务
     *
     * @param autoCommit
     * @return
     */
    public static SqlSession getSqlSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }

    /**
     * 提交事务以及关闭资源
     *
     * @param sqlSession
     */
    public static void commitAndClose(SqlSession sqlSession) {
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 回滚事务以及关闭资源
     *
     * @param sqlSession
     */
    public static void rollbackAndClose(SqlSession sqlSession) {
        sqlSession.rollback();
        sqlSession.close();
    }

}
