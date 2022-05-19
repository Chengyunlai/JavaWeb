package top.itifrd.pojo;


public class Student {

  private long id;
  private String studentName;
  private String studentPassword;
  private long teacherId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }


  public String getStudentPassword() {
    return studentPassword;
  }

  public void setStudentPassword(String studentPassword) {
    this.studentPassword = studentPassword;
  }


  public long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }

}
