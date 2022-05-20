package top.itifrd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Info {
  private Integer id;
  private String studentIdCard;
  private String info;
  // 查询每个学籍信息时，知道该学籍信息是属于哪个学生的
  private Student student;

  @Override
  public String toString() {
    return "Info{" +
            "id=" + id +
            ", info='" + info + '\'' +
            '}';
  }


  public String myselfToString(){
    return "Info{" +
            "id=" + id +
            ", studentIdCard='" + studentIdCard + '\'' +
            ", info='" + info + '\'' +
            ", student=" + student +
            '}';
  }
}
