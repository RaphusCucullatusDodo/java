package cn.raphuscucullatus.mybatis_plus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 员工类
 *
 * @author raphus cucullatus
 * @version 2021/9/12 14:49
 * @since JDK8
 */
// mp 默认将pojo类名当表明. 如果类名与表明不用,可使用@TableMame()注解解决
@TableName("tb1_employee")
public class Employee {
    private Integer id;
    private String lastName;
    private Integer age;
    private String email;
    private Integer gender;

    public Employee() {
    }

    public Employee(Integer id, String lastName, Integer age, String email, Integer gender) {
        this.id = id;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
