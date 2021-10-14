package cn.raphuscucullatus.java.web.foundational.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 *
 * @author raphus cucullatus
 * @version 2021/8/79:08
 * @since JDK8
 */
public class CookieUtil {
    /**
     * 增加Cookie
     */
    public static void addCookie(HttpServletResponse response, CookieConfig cookieConfig) {
        Cookie cookie = new Cookie(cookieConfig.getCookieName(), cookieConfig.getCookieValue());
        cookie.setMaxAge(cookieConfig.getMaxAge());
        cookie.setPath(cookieConfig.getPath());
        response.addCookie(cookie);
    }

    /**
     * 根据Cookie的cookieName获取Cookie信息
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (null != cookies && 0 < cookies.length) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 删除Cookie
     */
    public static void delCookie(HttpServletResponse response, CookieConfig cookieConfig) {
        Cookie cookie = new Cookie(cookieConfig.getCookieName(), cookieConfig.getCookieValue());
        cookie.setMaxAge(0);
        cookie.setPath(cookieConfig.getPath());
        response.addCookie(cookie);
    }

}
