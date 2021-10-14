package cn.raphuscucullatus.java.web.foundational.dao.impl;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Words;
import cn.raphuscucullatus.java.web.foundational.dao.WordsDAO;
import cn.raphuscucullatus.java.web.foundational.util.CustomQueryRunner;
import cn.raphuscucullatus.java.web.foundational.util.DruidDataSourceUtil;

import java.util.List;

/**
 * 搜索DAO实现
 *
 * @author raphus cucullatus
 * @version 2021/8/2118:54
 * @since JDK8
 */
public class WordsDAOImpl implements WordsDAO {

    CustomQueryRunner queryRunner = new CustomQueryRunner(DruidDataSourceUtil.getDataSource());

    @Override
    public List<Words> selectByWordS(String word) {
        String sql = "select * from jdbc_words where word like ? limit 0,10";
        if (null != word && !"".equals(word)) {
            List<Words> words = queryRunner.queryForList(sql, Words.class, "%" + word + "%");
            if (null != words && words.size() > 0) {
                return words;
            }
        }
        return null;
    }
}
