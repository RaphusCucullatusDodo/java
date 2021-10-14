package cn.raphuscucullatus.java.web.foundational.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 自定义Servlet实现文件下载
 *
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/6 1:57
 * @since JDK1.8
 */
@WebServlet("/download")
public class DowloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取下载的文件名
        String fileName = request.getParameter("filename");
        String mimeType = getServletContext().getMimeType(fileName);
//        获取mime类型 image/pipeg	jfif  并反应给浏览器
        System.out.println("下载文件" + fileName + "的mimeType为：" + mimeType);
        response.setHeader("content-Type", mimeType);

//        将文件名使用URL编码,解决客户端下载文件,文件名是中文时显示不正常的问题    java.net包下的 class URLEncoder
        String encodeFileName = URLEncoder.encode(fileName, "UTF-8");

//        设置响应头告诉 浏览器 要下载文件了
        response.setHeader("content-Disposition", "attachment;filename=" + encodeFileName);


        try (
                InputStream resourceAsStream = getServletContext().getResourceAsStream("images/" + fileName);
                ServletOutputStream outputStream = response.getOutputStream()
        ) {
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len=resourceAsStream.read(buffer))!=-1){
//            outputStream.write(buffer,0,len);
//        }
            IOUtils.copy(resourceAsStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
