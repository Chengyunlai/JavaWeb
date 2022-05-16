package top.itifrd.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName
 * @Description 使用Druid作为数据源的JDBC操作，通用的操作见DbUtils封装
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@Slf4j
public class DruidUtils {
    private static DataSource dataSource = null;
    private static Connection connection = null;
    // 在静态代码块中初始化参数
    static {
        Properties prop = new Properties();
        InputStream instream = null;
        try {
            instream = Resources.getResourceAsStream("resources/druid.properties");
            Boolean flag = (instream == null);
            log.error("使用Resources得到的instream为空:" + flag);
            ClassLoader classLoader = DruidUtils.class.getClassLoader();
            // instream = classLoader.getSystemResourceAsStream("resources/druid.properties");
            if (instream == null){
                log.info("未读取到druid.properties文件，系统自定义Druid设置");
                prop.setProperty("driverClassName","com.mysql.jdbc.Driver");
                prop.setProperty("url","jdbc:mysql://127.0.0.1:3306/chengyunlaidb?useSSL=false&Unicode=true&characterEncoding=utf8");
                prop.setProperty("username","root");
                prop.setProperty("password","root");
                prop.setProperty("initialSize","5");
                prop.setProperty("maxActive","10");
                prop.setProperty("maxWait","3000");
            }else {
                log.info("读取到druid.properties文件");
                prop.load(instream);
            }
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try {
            // 从数据池中获取连接
            connection = dataSource.getConnection();
            if (null == connection){
                log.info(connection + "是空的");
            }
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
