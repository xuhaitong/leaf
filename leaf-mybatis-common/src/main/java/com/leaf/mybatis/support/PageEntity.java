package com.leaf.mybatis.support;

import java.io.Serializable;

public class PageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pageNo = 1;

	private int pageSize = 10;
	private String orderBy;

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
