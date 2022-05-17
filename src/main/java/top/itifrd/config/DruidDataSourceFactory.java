package top.itifrd.config;

import com.alibaba.druid.pool.DruidDataSource;
import javafx.application.Application;
import org.apache.ibatis.datasource.DataSourceFactory;
import top.itifrd.utils.DruidUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName
 * @Description 整合Mybatis和Druid
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class DruidDataSourceFactory implements DataSourceFactory {
    private Properties properties;
    
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dataSource = DruidUtils.getDataSource();
        try {
            dataSource.init();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (dataSource);
    }
}
