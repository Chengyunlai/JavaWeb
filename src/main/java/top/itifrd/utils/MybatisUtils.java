package top.itifrd.utils;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            InputStream instream = Resources.getResourceAsStream("resources/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(instream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MybatisUtils(){};

    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    public static <E> E getSqlSession(Class<E> clazz){
        // return sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        E mapper = sqlSession.getMapper(clazz);
        return mapper;
    }
}
