package cn.raphuscucullatus.java.web.foundational.dao.impl;

import cn.raphuscucullatus.java.web.foundational.dao.UserDao;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.util.JDBCUtil;
//import sun.reflect.misc.ConstructorUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 基于Statement实现用户的增删改查操作
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/209:54
 * @since JDK8
 */
public class StatementUserDaoImpl implements UserDao {

    public StatementUserDaoImpl() {
    }

    @Override
    public int insert(User user) {
        if (null != user && null != user.getPassword() && null != user.getPassword()) {
            String name = user.getName();
            String password = user.getPassword();
            String sql = "insert into jdbc_user values(null,'" + name + "','" + password + "',now(),now())";
            try (
                    //    1 加载驱动    2 获得连接
                    Connection connection = JDBCUtil.getConnection();
                    //        3 获得执行SQL语句的Statement对象
                    Statement statement = connection.createStatement()
            ) {
                //        4 执行SQL语句获得结果集
                System.out.println("新增用户执行的SQL语句是" + sql);
                int row = statement.executeUpdate(sql);
                return row;
                //        5 处理结果

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

//        6 自动释放资源
        return 0;
    }

    @Override
    public int delete(User userCondition) {
        String sql = null;
//        注册驱动 建立连接 获取执行 返回结果 处理结果 释放资源
        if (null != userCondition && null != userCondition.getId()) {
//            通过ID删除
            sql = "delete from jdbc_user where id =" + userCondition.getId();
        } else if (null != userCondition && null != userCondition.getName()) {
//            通过字段名删除
            sql = "delete from jdbc_user where name = " + "'" + userCondition.getName() + "'";
        }
        try (
                Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (sql != null && sql != "") {
                System.out.println("删除用户使用的sql语句为:" + sql);
                int row = statement.executeUpdate(sql);
                return row;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(User userCondition) {
//        注册驱动 获取连接 获取SQL执行 获取执行结果 处理结果 关闭资源
        String sql = null;
        try (
                Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (null != userCondition && null != userCondition.getId() && null != userCondition.getName()) {
                sql = "update jdbc_user set name = '" + userCondition.getName() + "'" + " where id = " + userCondition.getId();
            } else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
                sql = "update jdbc_user set password = '" + userCondition.getPassword() + "'" + " where name = " + "'" + userCondition.getName() + "'";
            }
            if (null != sql && "" != sql) {
                int row = statement.executeUpdate(sql);
                return row;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> select(User userCondition) {
        String sql = "";
        try (
                Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
//            查询所有
            if (null == userCondition) {
                sql = "select * from jdbc_user";
            }
//            2.根据ID查询单个用户
            else if (null != userCondition && null != userCondition.getId()) {
                sql = "select * from jdbc_user where id = " + userCondition.getId();
            }
//           根据用户名和密码查询单个用户(登入业务)
            else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
                sql = "select * from jdbc_user where name = '" + userCondition.getName()
                        + "' and password = " + "'" + userCondition.getPassword() + "'";
            }
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                Timestamp createDate = resultSet.getTimestamp("create_date");
                Timestamp updateDate = resultSet.getTimestamp("update_date");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setPassword(password);
                user.setCreateDate(createDate.toLocalDateTime());
                user.setUpdateDate(updateDate.toLocalDateTime());
                userList.add(user);
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public long count() {
        String sql = "select count(*) from jdbc_user";
        Connection connection = JDBCUtil.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            long count = resultSet.getInt(0);
            System.out.println("总人数为" + count);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
