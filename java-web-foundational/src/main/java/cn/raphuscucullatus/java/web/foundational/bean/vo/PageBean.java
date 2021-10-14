package cn.raphuscucullatus.java.web.foundational.bean.vo;

import java.util.List;

/**
 * 分页对象
 *
 * @author raphus cucullatus
 * @version 2021/8/1611:44
 * @since JDK8
 */
public class PageBean<T> {
    /**
     * 总条数
     */
    private Long totalCount;

    /**
     * 分页数据
     */
    private List<T> dataList;

    /**
     * 页码
     */
    private Long pageNo;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Long totalPage;


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", dataList=" + dataList +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", pageNo=" + pageNo +
                '}';
    }
}
