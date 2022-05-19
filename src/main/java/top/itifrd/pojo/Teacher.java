package top.itifrd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
  private long id;
  private String teacherName;
  private String teacherPassword;
  private List<Student> students;
}
