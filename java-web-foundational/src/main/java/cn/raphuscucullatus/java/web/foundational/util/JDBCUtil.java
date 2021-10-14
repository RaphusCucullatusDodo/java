package cn.raphuscucullatus.java.web.foundational.util;


import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 封装 加载驱动 获取连接方法
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/219:53
 * @since JDK8
 */
public final class JDBCUtil {
    private static String url;
    private static String name;
    private static String password;
    private static String driver;

    //    读取配置文件
    static {
        Properties properties = new Properties();
        try {
//            解析jdbc.properties配置文件,并给成员变量赋值
            properties.load(JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            driver = (String) properties.get("jdbc.driver");
            url = (String) properties.get("jdbc.url");
            name = (String) properties.get("jdbc.userName");
            password = (String) properties.get("jdbc.userPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    1.加载驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //    通过驱动获取连接
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

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
