package cn.raphuscucullatus.java.web.foundational.util;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * 自定义连接池 - 高级版
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2315:59
 * @since JDK8
 */
public final class CustomDataSourceV2 implements DataSource {
    private static final LinkedList<Connection> connectionPool = new LinkedList<>();

    static {
        for (int i = 0; i < 5; i++) {
            Connection connection = JDBCUtil.getConnection();
            connectionPool.add(connection);
        }
        System.out.println("[v2]初始化链接池,当前连接池可用连接数量为:" + connectionPool.size());
    }

    /**
     * 获取连接
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        System.out.println("[v2]获取连接前,当前连接池连接数量为:" + connectionPool.size());
        Connection connection = null;
//        连接池是否有可用链接
        if (connectionPool.size() > 0) {
            //直接返回
            connection = connectionPool.removeFirst();
            System.out.println("[v2]获取连接后,当前连接池连接数量为:" + connectionPool.size());
            ConnectionDecorator connectionDecorator = new ConnectionDecorator(connection, connectionPool);
            System.out.println("当前使用连接池类名为" + connectionDecorator.getClass());
//            创建并返回装饰类对象
            return connectionDecorator;

        } else {
//            创建连接
            connection = JDBCUtil.getConnection();
            System.out.println("[v2]当前连接池连接数量为:" + connectionPool.size() + ",手动创建连接对象" + connection);
        }
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
