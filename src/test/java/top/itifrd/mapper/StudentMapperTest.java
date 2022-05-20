package top.itifrd.mapper;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.itifrd.pojo.Student;
import top.itifrd.utils.MybatisUtils;

import java.util.List;

import static org.junit.Assert.*;

public class StudentMapperTest {
    SqlSession sqlSession = null;
    StudentMapper mapper = null;
    @Before
    public void init(){
        sqlSession = MybatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void commit(){
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getAllStudent() {
        List<Student> allStudent = mapper.getAllStudent();
        // System.out.println(allStudent);
        for (Student student : allStudent) {
            String res = JSON.toJSONString(student);
            System.out.println(res);
        }
    }

    @Test
    public void selectById() {
        Student student = mapper.selectById("330127199808110000");
        String res = JSON.toJSONString(student);
        System.out.println(res);
    }
}