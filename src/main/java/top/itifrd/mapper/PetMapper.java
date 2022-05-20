package top.itifrd.mapper;

import org.apache.ibatis.annotations.Param;
import top.itifrd.pojo.Person;
import top.itifrd.pojo.Pet;

import java.util.List;

public interface PetMapper {
    // 通过宠物姓名查询信息，级联查询该宠物是属于哪个主人
    public Pet getPetInfoByName(@Param("name") String petName);
}
