package top.itifrd.mybatisTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import top.itifrd.mapper.UserMapper;
import top.itifrd.pojo.User;
import top.itifrd.utils.MybatisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class SqlSessionFactoryTest {
    @Test
    public void testGetSqlSessionFactory() throws IOException {
        // 获取mybatis-config文件流
        InputStream instream = Resources.getResourceAsStream("resources/mybatis-config.xml");
        System.out.println(instream);
        // 通过SqlSessionFactoryBuilder().build(文件流) 构造 sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(instream);
        System.out.println(sqlSessionFactory);
    }

    @Test
    public void getAll() throws IOException {
        InputStream instream = Resources.getResourceAsStream("resources/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(instream);
        // 从SqlSessionFactory中获取SqlSession
        // SqlSession 提供了在数据库执行 SQL 命令所需的所有方法
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper对象
        // 现在有了一种更简洁的方式——使用和指定语句的参数和返回值相匹配的接口（比如 BlogMapper.class），现在你的代码不仅更清晰，更加类型安全，还不用担心可能出错的字符串字面值以及强制类型转换
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * @Description: 测试单例SqlSessionFactory
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2022/5/17
     */
    @Test
    public void getSqlSessionFactory(){
        SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory();
        SqlSessionFactory sqlSessionFactory1 = MybatisUtils.getSqlSessionFactory();
        SqlSessionFactory sqlSessionFactory2 = MybatisUtils.getSqlSessionFactory();
        System.out.println(sqlSessionFactory);
        System.out.println(sqlSessionFactory1);
        System.out.println(sqlSessionFactory2);
    }

    @Test
    public void getSqlSession(){
        // UserMapper sqlSession = MybatisUtils.getSqlSession(UserMapper.class);
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }


}
