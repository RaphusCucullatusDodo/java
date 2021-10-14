package cn.raphuscucullatus.java.web.foundational.dao.impl;

import cn.raphuscucullatus.java.web.foundational.dao.UserDao;
import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.util.CustomQueryRunner;
import cn.raphuscucullatus.java.web.foundational.util.DruidDataSourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义QueryRunner实现用户增删改
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/260:44
 * @since JDK8
 */
public class CustomQueryRunnerUserDaoImpl implements UserDao {
    /**
     * 创建QueryRunner对象
     * 通过Druid工具类 注册驱动  获得连接 获取sql执行的对象(queryRunner)
     */
    private final CustomQueryRunner queryRunner = new CustomQueryRunner(DruidDataSourceUtil.getDataSource());

    @Override
    public int insert(User user) {
        if (null != user && null != user.getPassword() && null != user.getPassword()) {
            String sql = "insert into jdbc_user values (null,?,?,now(),now())";
            int row = queryRunner.update(sql, user.getName(), user.getPassword());
            return row;
        }
        return 0;
    }

    @Override
    public int delete(User userCondition) {
        String sql = null;
        if (null != userCondition && null != userCondition.getId()) {
//            通过ID删除
            sql = "delete from jdbc_user where id = ?";
            int row = queryRunner.update(sql, userCondition.getId());
            return row;
        } else if (null != userCondition && null != userCondition.getName()) {
//            通过字段名删除
            sql = "delete from jdbc_user where name = ? and password = ?";
            int row = queryRunner.update(sql, userCondition.getName(), userCondition.getPassword());
            return row;
        }
        return 0;
    }

    @Override
    public int update(User userCondition) {
        String sql = null;
//        根据id修改name
        if (null != userCondition && null != userCondition.getId() && null != userCondition.getName()) {
            sql = "update jdbc_user set name = ? where id = ?";
            int row = queryRunner.update(sql, userCondition.getName(), userCondition.getId());
            return row;
//            根据name修改password
        } else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
            sql = "update jdbc_user set password = ? where name = ?";
            int row = queryRunner.update(sql, userCondition.getPassword(), userCondition.getName());
            return row;
        }
        return 0;
    }

    @Override
    public List<User> select(User userCondition) {
        String sql = null;

        if (null == userCondition) {
            sql = "select * from jdbc_user";
            List<User> userList = queryRunner.queryForList(sql, User.class);
            if (null != userList) {
                return userList;
            }
        }
//            2.根据ID查询单个用户
        else if (null != userCondition && null != userCondition.getId()) {
            sql = "select * from jdbc_user where id = ?";
            User user = queryRunner.queryForObject(sql, User.class, userCondition.getId());
            if (null != user) {
                ArrayList<User> userList = new ArrayList<>();
                userList.add(user);
                return userList;
            }
//           根据用户名和密码查询单个用户(登入业务)
        } else if (null != userCondition && null != userCondition.getName() && null != userCondition.getPassword()) {
            sql = "select * from jdbc_user where name = ? and password = ?";
            User user = queryRunner.queryForObject(sql, User.class, userCondition.getName(), userCondition.getPassword());
            if (null != user) {
                ArrayList<User> userList = new ArrayList<>();
                userList.add(user);
                return userList;
            }
//             根据用户名查询用户
        } else if (null != userCondition && null != userCondition.getName()) {
            sql = "select * from jdbc_user where name = ?";
            User user = queryRunner.queryForObject(sql, User.class, userCondition.getName());
            if (null != user) {
                ArrayList<User> userList = new ArrayList<>();
                userList.add(user);
                return userList;
            }
        }
        return null;
    }

    @Override
    public long count() {
        String sql = "select count(*) from jdbc_user";
        long result = queryRunner.queryForType(sql, long.class);

        return result;
    }
}
