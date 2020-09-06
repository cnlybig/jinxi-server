package com.jinxi.star.jxserver.model.common;

import com.github.pagehelper.Page;

import java.util.List;

public class DataResult<T> {
	private List<T> data;
	private Paging paging;
	
	public DataResult(Page<T> page){
		this.data = page.getResult();
	    Paging paging = new Paging();
	    paging.setActualPageSize(this.data.size());
	    paging.setPageNo(page.getPageNum());
	    paging.setPageSize(page.getPageSize());
	    paging.setTotalPage(page.getPages());
	    this.paging = paging;
	}
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
