package com.jinxi.star.jxserver.model.common;

/**
 * @author: Shiz
 * @date: 2020/6/30 14:01
 */
public class ApiRequest<T> {
    int pageNo;
    int pageSize;
    T data;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
