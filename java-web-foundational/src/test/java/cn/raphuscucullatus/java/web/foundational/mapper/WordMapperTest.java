package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Words;
import cn.raphuscucullatus.java.web.foundational.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 关键字所搜(模糊查询)
 *
 * @author raphus cucullatus
 * @version 2021/8/3023:51
 * @since JDK8
 */
public class WordMapperTest {
    private SqlSession sqlSession = SqlSessionUtil.getSqlSession();

    @Test
    public void testSelectByWord() {
        WordsMapper wordsMapper = sqlSession.getMapper(WordsMapper.class);
        List<Words> wordsList = wordsMapper.selectByWord("iphone");
        for (Words words : wordsList) {
            System.out.println("查询结果:" + words);
        }
    }
}
