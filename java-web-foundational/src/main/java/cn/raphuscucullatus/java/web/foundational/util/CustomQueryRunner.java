package cn.raphuscucullatus.java.web.foundational.util;


import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 自定义DbUtils实现增删改
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2517:15
 * @since JDK8
 */
public class CustomQueryRunner {
    DataSource dataSource;


    public CustomQueryRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 数据的增删改
     *
     * @param sql    sql语句
     * @param params sql参数元数据
     * @return 受影响行数
     */
    public int update(String sql, Object... params) {
        if (null != dataSource && !"".equals(sql) && null != sql) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                //            获取sql参数元数据
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
                    //                给占位符赋值
                    preparedStatement.setObject(i, params[i - 1]);
                }
                int row = preparedStatement.executeUpdate();
                return row;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * 数据的增删改
     *
     * @param connection 连接
     * @param sql        sql语句
     * @param params     sql参数元数据
     * @return 受影响行数
     */
    public int update(Connection connection, String sql, Object... params) {
        if (null != dataSource && null != sql && !"".equals(sql)) {
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                //            获取sql参数元数据（占位符的类型）
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                //                    遍历元数据
                for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
                    //                给占位符赋值
                    preparedStatement.setObject(i, params[i - 1]);
                }
                int row = preparedStatement.executeUpdate();
                return row;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    /**
     * 查询结果是单行单列
     *
     * @param sql    sql语句
     * @param clazz  查询目标的运行时类
     * @param params sql参数元数据
     * @param <T>    泛型类型
     * @return 返回类型是一个泛型类型
     */
    public <T> T queryForType(String sql, Class<T> clazz, Object... params) {
        if (null != dataSource && null != sql && !"".equals(sql) && null != clazz) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
//                获取sql参数元数据
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
//                    为占位符赋值
                    preparedStatement.setObject(i, params[i - 1]);
                }

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    T result = resultSet.getObject(1, clazz);
                    return result;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 查询结果是单行多列
     * 一条记录对应一个java对象
     *
     * @param sql    sql语句
     * @param clazz  查询目标的运行时类
     * @param params 查询套件的参数
     * @param <T>    泛型类型
     * @return 返回类型是一个泛型类型
     */
    public <T> T queryForObject(String sql, Class<T> clazz, Object... params) {
        if (null != dataSource && null != sql && !"".equals(sql) && null != clazz) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
//                获取sql参数元数据 (即占位符类型)
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
//                    为占位符赋值
                    preparedStatement.setObject(i, params[i - 1]);
                }

                ResultSet resultSet = preparedStatement.executeQuery();
//                获取结果集元数据 (即 列的 类型 总数的等)
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                T instance = null;
                while (resultSet.next()) {
//                    给对象赋值
                    instance = clazz.newInstance();
//                  结果集列数
                    int columnCount = resultSetMetaData.getColumnCount();
//                    遍历所有列
                    for (int i = 1; i <= columnCount; i++) {
//                        获取列名
                        String columnName = resultSetMetaData.getColumnName(i);
//                        获取方法名
                        Method[] methods = clazz.getMethods();
                        for (Method method : methods) {
                            String methodName = method.getName();
                            if (methodName.startsWith("set") && compareDataBaseColumnNameFieldName(columnName, methodName)) {
                                method.invoke(instance, resultSet.getObject(i));
                            }
                        }
                    }
                }
                return instance;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 查询结果是多行多列
     * 一条记录对应一个List集合元素
     *
     * @param sql    SQL语句
     * @param clazz  查询目标的运行时类
     * @param params 查询套件的参数
     * @param <T>    泛型类型
     * @return 返回类型是一个泛型类型List
     */
    public <T> List<T> queryForList(String sql, Class<T> clazz, Object... params) {
        if (null != dataSource && null != sql && !"".equals(sql) && null != clazz) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
//                获取sql参数元数据 (即占位符类型)
                ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
                for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
//                    为占位符赋值
                    preparedStatement.setObject(i, params[i - 1]);
                }

                ResultSet resultSet = preparedStatement.executeQuery();
//                获取结果集元数据 (即 列的 类型 总数的等)
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                T instance;
                ArrayList<T> objList = new ArrayList<>();
//                循环处理多个单行多列数据 每次循环封装一行
                while (resultSet.next()) {
//                  结果集列数
                    int columnCount = resultSetMetaData.getColumnCount();
//                  创建实体类对象
                    instance = clazz.newInstance();
//                    遍历所有列, 使用合适的方法将数据封装到实体类中 一次循环封装一个列
                    for (int i = 1; i <= columnCount; i++) {
//                        获取列名
                        String columnName = resultSetMetaData.getColumnName(i);
//                        获取javabean方法名
                        Method[] allMethod = clazz.getMethods();
//                        找到符合条件的方法（和列名对应的放set方法）
                        for (Method method : allMethod) {
                            String methodName = method.getName();
//                            属性名 等于 列名 时 进行封装（方法名和列名相对应则执行该方法）
                            if (methodName.startsWith("set") && compareDataBaseColumnNameFieldName(columnName, methodName)) {
                                method.invoke(instance, resultSet.getObject(i));
                            }
                        }
                    }
                    if (null != instance) {
                        objList.add(instance);
                    }
                }
                if (null != objList && objList.size() > 0) {
                    return objList;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 比较 属性 与 列名 是否相等
     *
     * @param dataBaseColumnName 列名
     * @param methodName         方法名
     * @return 列名与属性名是否对应
     */
    private boolean compareDataBaseColumnNameFieldName(String dataBaseColumnName, String methodName) {
        String columnName;
//        id,name,balance,status,create_date,update_date
        if (dataBaseColumnName.contains("_")) {
            String[] columnNameSplit = dataBaseColumnName.split("_");
            columnName = columnNameSplit[0];
            for (int i = 1; i < columnNameSplit.length; i++) {
                //            sql列名命名规范 -> java变量命名规范   create _ d ate --> create Date
                columnName = columnName.concat(Character.toString(columnNameSplit[i].charAt(0)).toUpperCase()).
                        concat(columnNameSplit[i].substring(1));
            }
        } else {
            columnName = dataBaseColumnName;
        }
//        //        id,name,balance,status,create_date,update_date
//        方法名 -> 属性名   set CreateDate  --> createDate
        String substring = methodName.substring(4);
        String fieldName = Character.toString(methodName.charAt(3)).toLowerCase().concat(substring);

        return fieldName.equals(columnName);
    }

}
