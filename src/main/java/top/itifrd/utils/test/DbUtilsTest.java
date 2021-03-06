package top.itifrd.utils.test;
import org.junit.Test;
import top.itifrd.pojo.Book;
import top.itifrd.pojo.User;
import top.itifrd.utils.DbUtils;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class DbUtilsTest {

    @Test
    public void getMySqlConnection() {
        Connection connection = DbUtils.getMySqlConnection();
        if (connection!=null){
            System.out.println("获取成功");
            System.out.println(connection);
        }

    }

    @Test
    public void addData() {
        // 给操作的sql语句
        String sql = "insert into book (type,name,description) values (?,?,?)";
        // 给操作的连接
        Connection connection = DbUtils.getMySqlConnection();
        // 给操作的参数(直接写在参数中)
        int i = DbUtils.exec(sql, connection, "人物传记", "小小卒", "全栈开发狮");
    }

    @Test
    public void deleteData() {
        // 给操作的sql语句
        String sql = "delete from book where id = ?";
        // 给操作的连接
        Connection connection = DbUtils.getMySqlConnection();
        // 给操作的参数(直接写在参数中)
        int i = DbUtils.exec(sql, connection, 5);
    }

    @Test
    public void updateData() {
        // 给操作的sql语句
        String sql = "update book set type = ? where id = ?";
        // 给操作的连接
        Connection connection = DbUtils.getMySqlConnection();
        // 给操作的参数(直接写在参数中)
        int i = DbUtils.exec(sql, connection, "英雄记",1);
    }

    @Test
    public void selectAllData() {
        // 给操作的sql语句
        String sql = "select * from book";
        // 给操作的连接
        Connection connection = DbUtils.getMySqlConnection();
        ArrayList<Book> all = DbUtils.getAll(Book.class,sql, connection);
        System.out.println(all);
    }

    @Test
    public void getOneByCondition() {
        Connection connection = DbUtils.getMySqlConnection();
        String sql = "select * from user where (username=? and password=?)";
        User user = DbUtils.getOneByCondition(User.class, sql, connection, "cengyunlai", "123456");
        if (user!=null){
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }
    }
}