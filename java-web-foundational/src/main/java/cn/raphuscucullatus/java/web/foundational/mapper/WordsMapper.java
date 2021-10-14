package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Words;

import java.util.List;

/**
 * 关键字搜索(模糊查询)-基于MyBatis实现
 *
 * @author raphus cucullatus
 * @version 2021/8/3023:12
 * @since JDK8
 */
public interface WordsMapper {

    /**
     * 根据关键字匹配模糊列表
     *
     * @param keyWord
     * @return 匹配结果列表
     */
    List<Words> selectByWord(String keyWord);
}
