package top.itifrd.utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class JDBCUtils {
    public static Connection connection = null;

    static {
        // 解决中文乱码
        String url = "jdbc:mysql://127.0.0.1:3306/chengyunlaidb?useSSL=false&Unicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "root";
        try {
            // 在"com.mysql.jdbc"这个路径下找Driver类，将其加载到内存中
            // mysql5之后省略该语句
            // Class.forName("com.mysql.jdbc.Driver");
            // 原生获取连接
            connection = (Connection) DriverManager.getConnection(url,username,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection(){
        if (connection!=null){
            return connection;
        }else {
            return null;
        }
    }

    public static void closeConnection(java.sql.Connection connection){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
