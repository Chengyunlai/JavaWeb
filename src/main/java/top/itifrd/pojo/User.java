package top.itifrd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("user")
public class User {
    private Integer id = null;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    private Integer age;
    private String description;


    public User(String username, String password, Integer age, String description) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.description = description;
    }
}
