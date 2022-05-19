package top.itifrd.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.itifrd.pojo.User;
import top.itifrd.utils.MybatisUtils;

import static org.junit.Assert.*;

public class UserMapperTest {
    SqlSession sqlSession = null;
    UserMapper mapper = null;
    @Before
    public void init(){
        sqlSession = MybatisUtils.getSqlSession();
        mapper= sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void commit(){
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectAll() {
        mapper.selectAll();
    }

    @Test
    public void getOneById() {
        User oneById = mapper.getOneById(2);
    }

    @Test
    public void selectUserByUsername() {
        Integer i = mapper.selectUserByUsername("chengyunlai");
        System.out.println(i);
    }

    @Test
    public void updateUser() {
        User user = new User(11,"chengyunlai","1231",15,"person");
        mapper.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        mapper.deleteUserById(1);
    }

    @Test
    public void addUser() {
        User user = new User("chengyunlai","1231",15,"person");
        int i = mapper.addUser(user);
        System.out.println(i);
    }
}