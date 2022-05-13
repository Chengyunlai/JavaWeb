package top.itifrd.utils.test;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.junit.Test;
import top.itifrd.pojo.Book;
import top.itifrd.utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtilsTest {
    Connection connection = null;
    @Test
    public void getConnection() {
        connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }

    @Test
    public void selectAll(){
        connection = JDBCUtils.getConnection();
        // 定义sql语句
        String sql = "select * from book";
        // 获取执行sql的对象 Statement
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 执行sql语句
            ResultSet res = preparedStatement.executeQuery(sql);
            System.out.println(res);
            while (res.next()){
                Integer id = res.getInt("id");
                String type = res.getString("type");
                String name = res.getString("name");
                String description = res.getString("description");
                Book book = new Book(id,type,name,description);
                System.out.println(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
    }

}