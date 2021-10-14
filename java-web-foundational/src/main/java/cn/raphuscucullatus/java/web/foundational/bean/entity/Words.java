package cn.raphuscucullatus.java.web.foundational.bean.entity;

/**
 * 关键字(异步请求搜索自动补全)
 *
 * @author raphus cucullatus
 * @version 2021/8/2118:48
 * @since JDK8
 */
public class Words {
    private long id;
    private String word;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }
}
