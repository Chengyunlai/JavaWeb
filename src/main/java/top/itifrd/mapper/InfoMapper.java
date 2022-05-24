package top.itifrd.mapper;

import org.apache.ibatis.annotations.Param;
import top.itifrd.pojo.Info;

public interface InfoMapper {
    public Info selectByIdCard(@Param("id") String idCard);
}
