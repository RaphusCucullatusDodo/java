package cn.raphuscucullatus.java.web.foundational.dao;

import com.mysql.cj.jdbc.Driver;
import org.testng.annotations.Test;

import java.sql.*;

/**
 * jdbc编程
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/1923:49
 * @since JDK8
 */
public class JDBCTest {
    /**
     * 使用JDBC API查询jdbc_user表的数据
     */
    @Test
    public void testSelectJDBCUserTableDataV1() {
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String userName = "root";
        String userPassword = "088.5741";
        try {
//       1. 注册驱动
            DriverManager.registerDriver(new Driver());
//       2. 获得连接
            Connection connection = DriverManager.getConnection(url, userName, userPassword);
//       3.获取执行SQL语句的对象
            Statement statement = connection.createStatement();
//       4.执行SQL获取返回结果 得到结果集
            ResultSet resultSet = statement.executeQuery("select * from jdbc_user");
//       5.处理结果
            while (resultSet.next()) {
//               获取表中记录
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                Date createDate = resultSet.getDate("create_date");
                Date updateDate = resultSet.getDate("update_date");
//                打印记录
                System.out.println("id:" + id + ",name:" + name + ",password:" + password + ",create_date:" + createDate + ",update_date:" + updateDate);
            }
//       6.关闭资源 手动关闭
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectJDBCUserTableDataV2() {
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String userName = "root";
        String userPassword = "088.5741";

        try (
//       2. 获得连接
                Connection connection = DriverManager.getConnection(url, userName, userPassword);
//       3.获取执行SQL语句的对象
                Statement statement = connection.createStatement();
//       4.执行SQL获取返回结果 得到结果集
                ResultSet resultSet = statement.executeQuery("select * from jdbc_user");
        ) {
//       1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
//       5.处理结果
            while (resultSet.next()) {
//               获取表中记录
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                Date createDate = resultSet.getDate("create_date");
                Date updateDate = resultSet.getDate("update_date");
//                打印记录
                System.out.println("id:" + id + ",name:" + name + ",password:" + password + ",create_date:" + createDate + ",update_date:" + updateDate);
            }
//       6.自动关闭资源
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}