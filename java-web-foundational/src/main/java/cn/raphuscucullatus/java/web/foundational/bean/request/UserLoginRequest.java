package cn.raphuscucullatus.java.web.foundational.bean.request;

/**
 * 用户登入的请求数据封装
 *
 * @author raphus cucullatus
 * @version 2021/8/2418:08
 * @since JDK8
 */
public class UserLoginRequest {
    private String name;
    private String password;

    public UserLoginRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
