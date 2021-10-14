package cn.raphuscucullatus.java.web.foundational.servlet;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Words;
import cn.raphuscucullatus.java.web.foundational.bean.response.ResponseBean;
import cn.raphuscucullatus.java.web.foundational.service.WordsService;
import cn.raphuscucullatus.java.web.foundational.service.impl.WordsServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.core.Request;
import java.io.IOException;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.util.List;

/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/8/21 22:15
 * @since JDK1.8
 */
@WebServlet("/words")
public class WordsServlet extends BaseServlet {

    private WordsService wordsService = new WordsServiceImpl();

    public void search(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ResponseBean<List<Words>> responseBean = new ResponseBean<>(Boolean.TRUE);

        try {
            String keyword = request.getParameter("keyword");
            List<Words> wordsList = wordsService.searchWords(keyword);
            responseBean.setData(wordsList);
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setErrorMessage("服务器异常");
            responseBean.setFlag(Boolean.FALSE);
        }

        String jsonString = JSON.toJSONString(responseBean);
        response.getWriter().write(jsonString);
        System.out.println("【异步请求自动补全】服务器响应的JSON字符串内容是:" + jsonString);
    }
}
