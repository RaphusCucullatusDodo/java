package cn.raphuscucullatus.java.web.foundational.dao;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Words;

import java.util.List;

/**
 * 搜索DAO
 *
 * @author raphus cucullatus
 * @version 2021/8/2118:51
 * @since JDK8
 */
public interface WordsDAO {
    /**
     * 根据关键字匹配模糊列表
     *
     * @param word
     * @return 匹配结果列表
     */
    List<Words> selectByWordS(String word) throws Exception;

}
