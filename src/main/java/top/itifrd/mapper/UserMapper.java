package top.itifrd.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.itifrd.pojo.User;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public interface UserMapper {
    List<User> selectAll();
    // 注解加xml混合开发方式,简单的语句使用注解，复杂的语句使用xml
    @Select("select * from user where id = #{id}")
    User getOneById(@Param("id") Integer id);
    @Select("select count(*) from user where username = #{username}")
    Integer selectUserByUsername(@Param("username") String username);
}
