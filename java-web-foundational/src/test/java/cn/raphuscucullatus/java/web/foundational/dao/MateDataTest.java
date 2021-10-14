package cn.raphuscucullatus.java.web.foundational.dao;


import cn.raphuscucullatus.java.web.foundational.util.JDBCUtil;
import org.testng.annotations.Test;

import java.sql.*;

/**
 * 数据库元数据的获取
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2515:57
 * @since JDK8
 */

public class MateDataTest {
    /**
     * SQL参数元数据的获取
     */
    @Test
    public void getParameterMetaData() {
        String sql = "select * from jdbc_user where name = ? and password = ?";
        try (
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
//            获取SQL参数个数
            int parameterCount = parameterMetaData.getParameterCount();
            System.out.println("获取SQL参数的个数是:" + parameterCount);
//            MySQL不能获取SQL参数的类型, 其他数据库也许能
//            int parameterType = parameterMetaData.getParameterType(1);
//            System.out.println("获取SQL参数的类型为:"+parameterType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取结果集元数据
     */
    @Test
    public void ResultSetMetaData() {
        String sql = "select * from jdbc_user where name = ? and password = ?";
        try (
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

            int parameterCount = parameterMetaData.getParameterCount();
            System.out.println("获取SQL参数的个数是:" + parameterCount);

            preparedStatement.setString(1, "raphus");
            preparedStatement.setString(2, "088.5741");
            ResultSet resultSet = preparedStatement.executeQuery();
//            结果集的元数据
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//            获取列的个数
            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println("jdbc_user表的列的个数:" + columnCount);
            System.out.println();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("获取结果集中指定列的列名" + resultSetMetaData.getColumnName(i));
                System.out.println("获取结果集中指定列的MySQL数据类型" + resultSetMetaData.getColumnTypeName(i));
                System.out.println("获取结果集中指定列的MySQL数据类型对应的Java类型" + resultSetMetaData.getColumnClassName(i));
                System.out.println("*********************************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


