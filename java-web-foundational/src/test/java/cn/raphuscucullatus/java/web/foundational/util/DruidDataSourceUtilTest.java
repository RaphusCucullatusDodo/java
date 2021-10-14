package cn.raphuscucullatus.java.web.foundational.util;

import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGAlterTableAlterColumnSetNotNull;
import org.testng.annotations.Test;

import java.sql.Connection;

/**
 * 德鲁伊数据源工具类测试用例
 *
 * @author raphus cucullatus
 * @version 2021/8/1120:38
 * @since JDK8
 */
public class DruidDataSourceUtilTest {
    /**
     * 测试多次获取的connection是否相同
     */
    @Test
    public void testGetConnectionFromDruidDataSource() {
        System.out.println(DruidDataSourceUtil.getConnection());
        System.out.println(DruidDataSourceUtil.getConnection());
    }

    /**
     * 使用ThreadLocal存取Connection
     */
    @Test
    public void testThreadLocalConnectionGetSet() {
        ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

        threadLocal.set(DruidDataSourceUtil.getConnection());
        System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());
//        在main线程中创建一个线程
        new Thread(() -> {
//            在新创建的线程中设置threadlocal中的值该为java
            threadLocal.set(DruidDataSourceUtil.getConnection());
            System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());

        }).start();
        System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());
    }
}
