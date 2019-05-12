package cn.itsource.maomaogo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装 分页数据的工具类
 * @param <T>
 */
public class PageList<T> {


    private Long total;/*总数据量*/

    private List<T> rows = new ArrayList<T>();/* 当前页的数据 */


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


    public PageList() {
    }

    public PageList(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }


}
