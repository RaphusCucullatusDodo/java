package cn.raphuscucullatus.java.web.foundational.bean.vo;

/**
 * 商品查询条件
 *
 * @author raphus cucullatus
 * @version 2021/8/3117:01
 * @since JDK8
 */
public class QueryVO<T> {
    /**
     * 当前页码
     */
    private Long pageNo;
    /**
     * 分页条数
     */
    private Long pageSize;
    /**
     * 偏移量
     */
    private Long offSet;
    /**
     * 查询条件
     */
    private T condition;

    public Long getOffSet() {
        return (pageNo - 1) * pageSize;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

}
