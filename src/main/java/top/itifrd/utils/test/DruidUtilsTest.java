package top.itifrd.utils.test;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.itifrd.pojo.Book;
import top.itifrd.utils.DruidUtils;
import top.itifrd.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@Slf4j
public class DruidUtilsTest {
    private Connection connection = null;
    /**
     * @Description: 测试使用Druid作为数据源获取连接
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    @Test
    public void getConnection() {
        connection= DruidUtils.getConnection();
        if (connection != null){
            log.info("初始化成功:" + connection);
        }
        JDBCUtils.closeConnection(connection);
    }

    /**
     * @Description: JDBC查询
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    @Test
    public void selectAll(){
        connection = JDBCUtils.getConnection();
        // 定义sql语句
        String sql = "select * from book";
        // 获取执行sql的对象 preparedStatement
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 执行sql语句,从ResultSet对象中获取我们想要的数据
            ResultSet res = preparedStatement.executeQuery(sql);
            // 判断当前行是否为有效行，返回true:当前行有数据，返回false:当前行没有数据
            while (res.next()){
                // 然后通过列名获取该行，该列的值
                Integer id = res.getInt("id");
                String type = res.getString("type");
                String name = res.getString("name");
                String description = res.getString("description");
                Book book = new Book(id,type,name,description);
                log.info(book.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            // 手工JDBC的时候 需要我们人为的去释放
            JDBCUtils.closeConnection(connection);
        }
    }

    /**
     * @Description: JDBC修改
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    @Test
    public void UpdateData() throws SQLException {
        // 获取连接对象
        connection = JDBCUtils.getConnection();
        // 定义SQL语句
        String sql = "update book set type = ? where id = ?";
        // 获取执行sql的对象 preparedStatement
        PreparedStatement preparedStatement = null;
        try {
            // 取消事务的自动提交，在出现错误时，数据库可以回滚数据
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            // 设置SQL语句占位符中第一个参数
            preparedStatement.setString(1,"人物传记");
            // 设置SQL语句占位符中第二个参数
            preparedStatement.setString(2,"1");
            // 返回受影响行数
            int i = preparedStatement.executeUpdate();
            log.info("操作成功影响行数:"+ i);
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }finally {

        }
        JDBCUtils.closeConnection(connection);
    }

    /**
     * @Description: JDBC 增加
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2022/5/14
     */
    @Test
    public void addData() throws SQLException {
        // 获取连接的对象
        connection = JDBCUtils.getConnection();
        // 编辑SQL语句
        String sql = "insert into book (type,name,description) values (?,?,?)";
        // 定义执行sql语句的对象
        PreparedStatement preparedStatement = null;
        try {
            // 取消事务的自动提交
            connection.setAutoCommit(false);
            // 装填sql语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"人物传记");
            preparedStatement.setString(2,"一卒");
            preparedStatement.setString(3,"系统架构设计师");
            int i = preparedStatement.executeUpdate();
            log.info("操作成功影响行数" + i);
            // 事务的提交
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            // 失败回滚数据
            connection.rollback();
        } finally {
            // 关闭连接
            JDBCUtils.closeConnection(connection);
        }
    }

    @Test
    public void deleteData() throws SQLException {
        // 获取连接对象
        connection = JDBCUtils.getConnection();
        // 准备执行的sql语句
        String sql = "delete from book where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            // 将自动事务提交取消
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            // 将增加操作的数据删除
            preparedStatement.setString(1,"3");
            // 执行操作
            int i = preparedStatement.executeUpdate();
            log.info("操作成功影响行数:" + i);
            // 提交事务
            connection.commit();
        } catch (SQLException throwables) {
            // 出错时回滚
            connection.rollback();
            throwables.printStackTrace();
        }finally {
            // 关闭连接
            JDBCUtils.closeConnection(connection);
        }
    }

    @Test
    public void getPath(){
        System.out.println(this.getClass().getResource("/"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.class.path"));
    }

}
