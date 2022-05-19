package top.itifrd.mapper;

import org.apache.ibatis.annotations.Param;
import top.itifrd.pojo.Teacher;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public interface TeacherMapper {
    Teacher selectTeacherById(Integer id);
}
