package cn.raphuscucullatus.java.web.foundational.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * getMimeTypes 获取MIME类型
 * 获取Web资源的真实路径
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/2 0:25
 * @since JDK1.8
 */
@WebServlet("/context")
public class ServletContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
//        getMimeTypes(servletContext);
//        getWebResource(servletContext);
        InputStream contextResourceAsStream = getWebResourceAsStream(servletContext);
        System.out.println("WebResource输入流为:" + contextResourceAsStream);
        InputStream classLoaderResourceAsStream = getClassLoaderResourceAsStream();
        System.out.println("Resource输入流为:" + classLoaderResourceAsStream);
    }

    /**
     * 通过classLoader获取Web资源输入流
     *
     * @return
     */
    private InputStream getClassLoaderResourceAsStream() {
        InputStream resourceAsStream = ServletContextServlet.class.getClassLoader().getResourceAsStream("static\\html\\css_quickstart.html");
        return resourceAsStream;
    }

    /**
     * 通过ServletContext获取Web资源输入流
     *
     * @param servletContext
     * @return
     */
    private InputStream getWebResourceAsStream(ServletContext servletContext) {
        InputStream resourceAsStream = servletContext.getResourceAsStream("images/1ef03bceb4e76206.png");
        return resourceAsStream;
    }

    /**
     * 只能获取Web资源(webapp目录下)的真实路径
     *
     * @param servletContext
     */
    private void getWebResource(ServletContext servletContext) {
        String file = "index.html";
        String realPath = servletContext.getRealPath(file);
        System.out.println(file + "的真实路径为:" + realPath);
        file = "WEB-INF/web.xml";
        realPath = servletContext.getRealPath(file);
        System.out.println(file + "的真实路径为:" + realPath);


    }

    /**
     * 获取获取MIME类型
     *
     * @param servletContext
     */
    private void getMimeTypes(ServletContext servletContext) {
        String file = "index.html";
        String mimeType = servletContext.getMimeType(file);
        System.out.println(file + "的mimetype是:" + mimeType);
        file = "功夫.mp4";
        mimeType = servletContext.getMimeType(file);
        System.out.println(file + "的mimetype是:" + mimeType);
        file = "huaweimate30pro.jpg";
        mimeType = servletContext.getMimeType(file);
        System.out.println(file + "的mimetype是:" + mimeType);
        file = "web.xml";
        mimeType = servletContext.getMimeType(file);
        System.out.println(file + "的mimetype是:" + mimeType);
        file = "web.flac";
        mimeType = servletContext.getMimeType(file);
        System.out.println(file + "的mimetype是:" + mimeType);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
