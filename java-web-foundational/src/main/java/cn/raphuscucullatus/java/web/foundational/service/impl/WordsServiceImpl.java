package cn.raphuscucullatus.java.web.foundational.service.impl;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Words;
import cn.raphuscucullatus.java.web.foundational.dao.WordsDAO;
import cn.raphuscucullatus.java.web.foundational.dao.impl.WordsDAOImpl;
import cn.raphuscucullatus.java.web.foundational.service.WordsService;

import java.util.List;

/**
 * 关键字所搜业务实现类
 *
 * @author raphus cucullatus
 * @version 2021/8/2119:22
 * @since JDK8
 */
public class WordsServiceImpl implements WordsService {

    private WordsDAO wordsService = new WordsDAOImpl();

    @Override
    public List<Words> searchWords(String keyWord) {
        try {
            return wordsService.selectByWordS(keyWord);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
