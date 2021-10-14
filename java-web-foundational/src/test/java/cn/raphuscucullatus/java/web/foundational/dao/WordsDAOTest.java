package cn.raphuscucullatus.java.web.foundational.dao;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Words;
import cn.raphuscucullatus.java.web.foundational.dao.impl.WordsDAOImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 关键字搜索测试用例
 *
 * @author raphus cucullatus
 * @version 2021/8/2119:04
 * @since JDK8
 */
public class WordsDAOTest {
    WordsDAO wordsDAO = new WordsDAOImpl();

    @Test
    public void testSelectByWord() {
        List<Words> iphoneLike = null;
        try {
            iphoneLike = wordsDAO.selectByWordS("iphone");
            Assert.assertEquals(iphoneLike.size(), 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
