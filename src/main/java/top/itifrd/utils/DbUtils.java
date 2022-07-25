package top.itifrd.utils;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * @ClassName
 * @Description 使用Druid作为数据连接池，使用JDBC基础的数据库操作 做一个通用的数据库操作工具
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@Slf4j
public class DbUtils {
    // 提供连接对象
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    /**
     * @Description: 获取一个mysql的数据库连接对象
     * @Param: []
     * @return: java.sql.Connection
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    public static Connection getMySqlConnection(){
        connection = DruidUtils.getConnection();
        return connection;
    }

    /**
     * @Description: 通用的查询全部字段
     * @Param: []
     * @return: java.util.ArrayList<E>
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    public static <E> ArrayList<E> getAll(Class<E> clazz,String sql,Connection connection){
        ArrayList<E> arrayList = new ArrayList<>();
        try {
            // 通过反射得到实例对象
            E e;
            preparedStatement = DbUtils.getPreparedStatement(sql, connection);
            ResultSet resultSet = preparedStatement.executeQuery();
            // 获取表的结构
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取数据的行数
            // 获取数据的字段数
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                e = clazz.newInstance();
                // 每行对象封装
                for (int i = 1; i <= columnCount; i++) {
                    // 获取列名
                    String columnName = metaData.getColumnName(i);
                    // 通过列名获取值
                    Object object = resultSet.getObject(columnName);
                    // 通过反射获取该类的属性
                    Field field = clazz.getDeclaredField(columnName);
                    // 给属性注入内容，需要先开启Set
                    field.setAccessible(true);
                    // 注入内容
                    field.set(e,object);
                }
                arrayList.add(e);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> E getOneByCondition(Class<E> clazz,String sql,Connection connection,Object ...args){
        // ArrayList<E> arrayList = new ArrayList<>();
        try {
            // 通过反射得到实例对象
            E e = clazz.newInstance();
            preparedStatement = DbUtils.getPreparedStatement(sql, connection);
            // 条件查询注入
            DbUtils.addPreparedStatementAgrs(preparedStatement,args);
            ResultSet resultSet = preparedStatement.executeQuery();
            // 获取表的结构
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取数据的行数
            // 获取数据的字段数
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                // 每行对象封装
                for (int i = 1; i <= columnCount; i++) {
                    // 获取列名
                    String columnName = metaData.getColumnName(i);
                    // 通过列名获取元素
                    Object object = resultSet.getObject(columnName);
                    // 通过反射获取该类的属性
                    Field field = clazz.getDeclaredField(columnName);
                    // 给属性注入内容，需要先开启Set
                    field.setAccessible(true);
                    // 注入内容
                    field.set(e,object);
                }
                return e;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 通用的添加/删除/修改操作
     * @Param: [sql, connection, args] sql:传入操作的sql语句;connection:传入此次事务的连接对象;args:传入添加操作的参数
     * @return: int
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    public static int exec(String sql,Connection connection,Object ...args){
        try {
            preparedStatement = DbUtils.getPreparedStatement(sql,connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = DbUtils.execSql(sql, connection, preparedStatement, args);
        return i;
    }
    /**
     * @Description: 关闭mysql的数据库连接对象
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    public static void closeMysqlConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @Description: 封装一个操作sql语句的对象——PreparedStatement
     * @Param: [sql, connection] sql为装填的sql语句;connection作为对该连接生成一个操作sql语句的对象
     * @return: java.sql.PreparedStatement
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    private static PreparedStatement getPreparedStatement(String sql,Connection connection) throws Exception {
        if (sql.equals(null) || connection.equals(null)){
            throw new Exception("sql语句或者未获取到connection对象");
        }
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 装填PreparedStatement的参数
     * @Param: [preparedStatement, args]
     * @return: void
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    private static void addPreparedStatementAgrs(PreparedStatement preparedStatement,Object ...args){
        int i = 1;
        // 设置执行sql语句对象的参数
        for (Object arg : args) {
            try {
                preparedStatement.setObject(i++,arg);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * @Description: 将通用的增、删、改封装
     * @Param: [sql, connection, preparedStatement, args]
     * @return: int
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    private static int execSql(String sql,Connection connection,PreparedStatement preparedStatement,Object ...args){
        try {
            connection.setAutoCommit(false);
            preparedStatement = DbUtils.getPreparedStatement(sql, connection);
            DbUtils.addPreparedStatementAgrs(preparedStatement,args);
            int i = preparedStatement.executeUpdate();
            log.info("操作成功,影响的行数:" + i);
            connection.commit();
            return i;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            // 关闭连接
            DbUtils.closeMysqlConnection(connection);
        }
        return 0;
    }

}
