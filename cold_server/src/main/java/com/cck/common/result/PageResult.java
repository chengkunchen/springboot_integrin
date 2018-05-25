package com.cck.common.result;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by AAS on 2018/3/28.
 */
public class PageResult<T> extends Result {

    long count;
    int currentPage;
    int pageCount;


    public PageResult(List<T> t) {
        this.setCode(200);
        this.setMessage("SUCCESS");
        this.setData(t);
        PageInfo<T> pageInfo = new PageInfo<T>(t);
        this.count = pageInfo.getTotal();
        this.pageCount = pageInfo.getPages();
        this.currentPage = pageInfo.getPageNum();
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
