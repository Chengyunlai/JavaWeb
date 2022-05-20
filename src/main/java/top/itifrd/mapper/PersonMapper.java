package top.itifrd.mapper;

import top.itifrd.pojo.Person;

import java.util.List;

public interface PersonMapper {
    // 遍历所有人员及其养的所有宠物信息
    public List<Person> getPersonAndPet();
}
