package cn.raphuscucullatus.java.web.foundational.service;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Words;
import cn.raphuscucullatus.java.web.foundational.dao.WordsDAO;
import cn.raphuscucullatus.java.web.foundational.dao.impl.WordsDAOImpl;

import java.util.List;

/**
 * 关键字搜索Service
 *
 * @author raphus cucullatus
 * @version 2021/8/2119:18
 * @since JDK8
 */
public interface WordsService {

    /**
     * 根据关键字所搜匹配数据
     *
     * @param keyWord
     * @return
     */
    List<Words> searchWords(String keyWord);
}
