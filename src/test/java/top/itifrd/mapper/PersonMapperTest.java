package top.itifrd.mapper;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.itifrd.pojo.Person;
import top.itifrd.utils.MybatisUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonMapperTest {
    SqlSession sqlSession = null;
    PersonMapper mapper = null;
    @Before
    public void init(){
        sqlSession = MybatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(PersonMapper.class);
    }

    @After
    public void commit(){
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void getPersonAndPet() {
        List<Person> personAndPet = mapper.getPersonAndPet();
        for (Person person : personAndPet) {
            String res = JSON.toJSONString(person);
            System.out.println(res);
        }

    }
}