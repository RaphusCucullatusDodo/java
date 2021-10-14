package cn.raphuscucullatus.java.web.foundational.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/13 5:50
 * @since JDK1.8
 */
@WebFilter("/*")
public class IllegalCharacterFilter implements Filter {
    List<String> illegalCharacter;

    @Override
    public void init(FilterConfig config) throws ServletException {
        try (
                //        获取非法字符文件的字节输入流
                InputStream inputStream = IllegalCharacterFilter.class.getClassLoader().getResourceAsStream("illegal.txt");
                //        转化为字符输入流，因为illegal character内容都是字符
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String readLine = null;
            illegalCharacter = new ArrayList<>();
            while (null != (readLine = bufferedReader.readLine())) {
                illegalCharacter.add(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("当前illegal.txt非法字符列表为：");
        for (String s : illegalCharacter) {
            System.out.println(s);
        }

    }

    /**
     * 通过动态代理 我们增强了HttpServletRequest的getParameter()方法,改变了该方法的返回值
     * 将参数中 匹配上配置文件illegal.txt内的非法字符 替换为*
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        使用doFilter方法过滤非法字符
//        获取请求参数，并替换非法字符
//          使用动态代理增强HttpServletRequest的getParameter方法  （增强：继承，装饰 ，动态代理）
        HttpServletRequest proxyHttpServletRequest =
                (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader()
                        , new Class[]{HttpServletRequest.class}, (proxy, method, args) -> {

                            String proxyMethodName = "getParameter";
                            String returnValue = null;
//                增强getParameter方法
                            if (method.getName().equals(proxyMethodName)) {
//                    目的是改变getParameter返回值，替换为和谐后的值
                                returnValue = (String) method.invoke(httpServletRequest, args);
//                   替换非法字符
                                if (null != returnValue) {
                                    String replacereturnValue = returnValue;
//                        便利非法字符
                                    for (String s : illegalCharacter) {
//                            如果包含非法字符，则替换为*
                                        while (replacereturnValue.contains(s)) {
                                            String asterisk = "";
//                                根据非法字符长度决定*个数
                                            for (int i = 0; i < s.length(); i++) {
                                                asterisk += "*";
                                            }
                                            replacereturnValue = replacereturnValue.replace(s, asterisk);
                                            System.out.println("替换前：" + returnValue + ",替换后：" + replacereturnValue);
                                        }
                                    }
                                    returnValue = replacereturnValue;
                                }
                                return returnValue;
                            }
//              如果不是目的是改变getParameter方法，则执行原来的方法
                            return method.invoke(httpServletRequest, args);
                        });
//        放行一定要使用 proxyHttpServletRequest ，否则回去的参数还是没有和谐的字符
        chain.doFilter(proxyHttpServletRequest, response);
    }

    @Override
    public void destroy() {
    }
}
