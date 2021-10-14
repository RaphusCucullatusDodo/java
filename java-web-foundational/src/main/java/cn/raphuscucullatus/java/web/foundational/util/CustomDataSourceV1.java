package cn.raphuscucullatus.java.web.foundational.util;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 自定义连接池基础版
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2222:39
 * @since JDK8
 */
public final class CustomDataSourceV1 {
    private static final LinkedList<Connection> connectionPool = new LinkedList();

    static {
        for (int i = 0; i < 5; i++) {
            Connection connection = JDBCUtil.getConnection();
            connectionPool.add(connection);
        }
        System.out.println("初始化链接池,当前连接池可用连接数量为:" + connectionPool.size());
    }

    /**
     * 获得连接
     * 首先从连接池获取
     * 如果连接池没有连接则创建连接
     */
    public Connection getConnection() {
        System.out.println("获取连接前,当前连接池连接数量为:" + connectionPool.size());
        Connection connection = null;
//        连接池是否有可用链接
        if (connectionPool.size() > 0) {
            //直接返回
            connection = connectionPool.removeFirst();
            System.out.println("获取连接后,当前连接池连接数量为:" + connectionPool.size());
        } else {
//            创建连接
            connection = JDBCUtil.getConnection();
            System.out.println("当前连接池连接数量为:" + connectionPool.size() + ",手动创建连接对象" + connection);
        }
        return connection;
    }

    public void giveBackConnection(Connection connection) {
        connectionPool.addLast(connection);
        System.out.println("连接已归还,当前连接池可用连接数量为:" + connectionPool.size());

    }
}
