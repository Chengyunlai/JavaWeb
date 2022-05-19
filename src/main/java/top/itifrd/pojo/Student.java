package top.itifrd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  private long id;
  private String studentName;
  private String studentPassword;
  private Teacher teacher;


}
