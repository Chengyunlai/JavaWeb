package top.itifrd.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class DruidUtils {
    private static DataSource dataSource = null;
    private static Connection connection = null;
    // 在静态代码块中初始化参数
    static {
        Properties prop = new Properties();
        InputStream instream = null;
        try {
            // 文件读取流从工程的src文件下开始算即可
            instream = new FileInputStream("src/main/java/top/itifrd/druid.properties");
            prop.load(instream);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try {
            // 从数据池中获取连接
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
