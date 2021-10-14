package cn.raphuscucullatus.java.web.foundational.dao.impl;

import cn.raphuscucullatus.java.web.foundational.dao.UserDao;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.util.DruidDataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于DBUtils提供的QueryRunner实现的用户增删改查
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2423:08
 * @since JDK8
 */
public class QueryRunnerUserDaoImpl implements UserDao {
    /**
     * 创建QueryRunner对象
     * 通过Druid工具类 注册驱动  获得连接 获取sql执行的对象(queryRunner)
     */
    private final QueryRunner queryRunner = new QueryRunner(DruidDataSourceUtil.getDataSource());

    @Override
    public int insert(User user) {
        if (null != user && null != user.getPassword() && null != user.getPassword()) {
            String sql = "insert into jdbc_user values (null,?,?,now(),now())";
            try {
                int row = queryRunner.update(sql, user.getName(), user.getPassword());
                return row;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete(User userCondition) {
        String sql = null;
        if (null != userCondition && null != userCondition.getId()) {
//            通过ID删除
            sql = "delete from jdbc_user where id = ?";
            try {
                int row = queryRunner.update(sql, userCondition.getId());
                return row;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (null != userCondition && null != userCondition.getName()) {
//            通过字段名删除
            sql = "delete from jdbc_user where name = ? and password = ?";
            try {
                int row = queryRunner.update(sql, userCondition.getName(), userCondition.getPassword());
                return row;
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
            try {
                int row = queryRunner.update(sql, userCondition.getName(), userCondition.getId());
                return row;
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            根据name修改password
        } else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
            sql = "update jdbc_user set password = ? where name = ?";
            try {
                int row = queryRunner.update(sql, userCondition.getPassword(), userCondition.getName());
                return row;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public List<User> select(User userCondition) {
        String sql = null;
        //            查询所有
        if (null == userCondition) {
            sql = "select * from jdbc_user";
            try {
                List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class));
                if (null != userList && userList.size() > 0) {
                    return userList;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//            2.根据ID查询单个用户
        else if (null != userCondition && null != userCondition.getId()) {
            sql = "select * from jdbc_user where id = ?";
            try {
                User user = queryRunner.query(sql, new BeanHandler<User>(User.class), userCondition.getId());
                if (null != user) {
                    ArrayList<User> userList = new ArrayList<>();
                    userList.add(user);
                    return userList;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//           根据用户名和密码查询单个用户(登入业务)
        else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
            sql = "select * from jdbc_user where name = ? and password = ?";
            try {
                User user = queryRunner.query(sql, new BeanHandler<User>(User.class), userCondition.getName(), userCondition.getPassword());
                if (null != user) {
                    ArrayList<User> userList = new ArrayList<>();
                    userList.add(user);
                    return userList;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public long count() {
        String sql = "select count(*) from jdbc_user";
        Long count = null;
        try {
            count = queryRunner.query(sql, new ScalarHandler<>());
            System.out.println("总人数为:" + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
