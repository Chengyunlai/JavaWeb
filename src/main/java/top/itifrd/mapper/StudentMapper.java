package top.itifrd.mapper;

import org.apache.ibatis.annotations.Param;
import top.itifrd.pojo.Student;

import java.util.List;

public interface StudentMapper {
    // 查询所有学生信息
    public List<Student> getAllStudent();
    // 查询某个学生信息
    public Student selectById(@Param("id") String idCard);
}
