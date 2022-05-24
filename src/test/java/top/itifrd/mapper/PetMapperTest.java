package top.itifrd.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.itifrd.pojo.Pet;
import top.itifrd.utils.MybatisUtils;

import static org.junit.Assert.*;

public class PetMapperTest {
    SqlSession sqlSession = null;
    PetMapper mapper = null;
    @Before
    public void init(){
        sqlSession = MybatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(PetMapper.class);
    }

    @After
    public void commit(){
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getPetInfoByName() {
        Pet petInfoByName = mapper.getPetInfoByName("小黑");
        System.out.println(petInfoByName);
    }
}