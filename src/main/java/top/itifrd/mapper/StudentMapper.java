package top.itifrd.mapper;

import top.itifrd.pojo.Student;

import java.util.List;

public interface StudentMapper {
    // 查询所有学生的信息以及对应老师的信息
    public List<Student> getStudent();
}
