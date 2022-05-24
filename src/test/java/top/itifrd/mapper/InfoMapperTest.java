package top.itifrd.mapper;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.itifrd.pojo.Info;
import top.itifrd.utils.MybatisUtils;

import static org.junit.Assert.*;

public class InfoMapperTest {
    SqlSession sqlSession = null;
    InfoMapper mapper = null;
    @Before
    public void init(){
        sqlSession = MybatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(InfoMapper.class);
    }

    @After
    public void commit(){
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void selectByIdCard() {
        Info info = mapper.selectByIdCard("330127199808110000");
        System.out.println(JSON.toJSONString(info));
    }
}