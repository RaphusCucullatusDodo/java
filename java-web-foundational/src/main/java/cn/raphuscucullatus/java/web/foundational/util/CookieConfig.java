package cn.raphuscucullatus.java.web.foundational.util;

/**
 * Cookie的配置
 *
 * @author raphus cucullatus
 * @version 2021/8/79:08
 * @since JDK8
 */
public class CookieConfig {
    /**
     * cookie名称
     */
    private String cookieName;
    /**
     * cookie值
     */
    private String cookieValue;
    /**
     * cookie默认有效期
     */
    private int maxAge = 7 * 24 * 60 * 60;
    /**
     * cookie有效路径
     */
    private String path;


    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
