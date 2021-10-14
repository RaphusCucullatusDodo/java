package cn.raphuscucullatus.java.web.foundational.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 德鲁伊连接池 工具类
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2322:27
 * @since JDK8
 */
public class DruidDataSourceUtil {
    /**
     * 连接池
     */
    private static DataSource dataSource;
    /**
     * 创建一个threadlocal用于存放connection
     */
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

    /**
     *读取druid.properties
     */
    static {
        Properties properties = new Properties();
        try {
//            通过类加载器获得配置文件的输入流
            properties.load(DruidDataSourceUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
//            创建DruidDataSource
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取连接的方法
     * 保证同一个线程中获取的获取的Connection相同
     *
     * @return
     */
    public static Connection getConnection() {
//        先充ThreadLocal中获取
        Connection connection = CONNECTION_THREAD_LOCAL.get();
//        如果没为空，则在德鲁伊连接池获取连接
        if (null == connection) {
            try {
                connection = getDataSource().getConnection();
                CONNECTION_THREAD_LOCAL.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 释放资源
     *
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
