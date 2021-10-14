package cn.raphuscucullatus.java.web.foundational.dao.impl;

import cn.raphuscucullatus.java.web.foundational.dao.UserDao;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2115:38
 * @since JDK8
 */
public class PreparedStatementUserDaoImpl implements UserDao {


    @Override
    public int insert(User user) {
        if (null != user && null != user.getPassword() && null != user.getPassword()) {
//            表示预编译的sql语句,需要配置的参数用占位符?代替
            String sql = "insert into jdbc_user values (null,?,?,now(),now())";
            try (
                    //    1 加载驱动    2 获得连接
                    Connection connection = JDBCUtil.getConnection();
                    //        3 获得执行SQL语句的Statement对象
                    PreparedStatement prepareStatement = connection.prepareStatement(sql);
            ) {
//                给占位符设置参数
                prepareStatement.setString(1, user.getName());
                prepareStatement.setString(2, user.getPassword());
                //        4 执行SQL语句获得结果
                int row = prepareStatement.executeUpdate();
                return row;
                //        5 处理结果

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete(User userCondition) {
        if (null != userCondition) {
            String sql = null;
            if (null != userCondition.getId()) {
//            通过ID删除
                sql = "delete from jdbc_user where id = ?";
            } else if (null != userCondition.getName()) {
//            通过字段名删除
                sql = "delete from jdbc_user where name = ? and password = ?";
            }
            try (
//                  注册驱动 获取连接 获取SQL执行对象
                    Connection connection = JDBCUtil.getConnection();
                    PreparedStatement prepareStatement = connection.prepareStatement(sql)
            ) {
//                获取执行结果(先将占符补充完成整)
                if (null != userCondition && null != userCondition.getId()) {
//            通过ID删除
                    prepareStatement.setInt(1, userCondition.getId());
                } else if (null != userCondition && null != userCondition.getName()) {
//            通过字段名删除
                    prepareStatement.setString(1, userCondition.getName());
                    prepareStatement.setString(2, userCondition.getPassword());
                }
                int row = 0;
                if (null != sql && "" != sql) {
                    row = prepareStatement.executeUpdate();
                    return row;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int update(User userCondition) {
        String sql = null;
//        根据id修改name
        if (null != userCondition && null != userCondition.getId() && null != userCondition.getName()) {
            sql = "update jdbc_user set name = ? where id = ?";
//            根据name修改password
        } else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
            sql = "update jdbc_user set password = ? where name = ?";
        }
        try (
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            if (null != userCondition && null != userCondition.getId() && null != userCondition.getName()) {
                preparedStatement.setString(1, userCondition.getName());
                preparedStatement.setInt(2, userCondition.getId());
            } else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
                preparedStatement.setString(2, userCondition.getName());
                preparedStatement.setString(1, userCondition.getPassword());
            }
            if (null != sql && "" != sql) {

                int row = preparedStatement.executeUpdate();
                return row;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> select(User userCondition) {
        String sql = null;
        //            查询所有
        if (null == userCondition) {
            sql = "select * from jdbc_user";
        }
//            2.根据ID查询单个用户
        else if (null != userCondition && null != userCondition.getId()) {
            sql = "select * from jdbc_user where id = ?";
        }
//           根据用户名和密码查询单个用户(登入业务)
        else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
            sql = "select * from jdbc_user where name = ? and password = ?";
        }

        try (
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            if (null != sql && "" != sql) {
//            2.根据ID查询单个用户
                if (null != userCondition && null != userCondition.getId()) {
                    preparedStatement.setInt(1, userCondition.getId());
                }
                //           根据用户名和密码查询单个用户(登入业务)
                else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
                    preparedStatement.setString(1, userCondition.getName());
                    preparedStatement.setString(2, userCondition.getPassword());
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long count() {
        String sql = "select count(*) from jdbc_user";
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = resultSet.getInt(0);
            System.out.println("总人数为:" + count);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
