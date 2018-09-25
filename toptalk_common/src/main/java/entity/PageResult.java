package entity;

import java.util.List;

/**
* @Author: MasterCV
* @Date: 2018/9/25 18:34
* @Description: 用于返回分页结果的类
*/
public class PageResult<T> {

    private Long total;//总结果数
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
