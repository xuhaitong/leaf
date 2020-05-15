package com.leaf.boot.mybatis.support.plugins.pagination;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> records;
	private Integer pageCount = Integer.valueOf(0);

	private Integer pageSize = Integer.valueOf(10);

	private Integer pageIndex = Integer.valueOf(1);

	private Integer rowCount = Integer.valueOf(0);

	private String orderBy = "";

	public Pagination() {
	}

	public int getFirstResult() {
		return (this.pageIndex.intValue() - 1) * this.pageSize.intValue();
	}

	public Pagination(int pageSize, int pageIndex) {
		this.pageSize = Integer.valueOf(pageSize);
		this.pageIndex = Integer.valueOf(pageIndex);
	}

	public Pagination(int pageSize, int pageIndex, String orderBy) {
		this.pageSize = Integer.valueOf(pageSize);
		this.pageIndex = Integer.valueOf(pageIndex);
		this.orderBy = orderBy;
	}

	public Pagination(int pageIndex) {
		this.pageIndex = Integer.valueOf(pageIndex);
	}

	public void setQueryResult(int rowCount, List<T> records) {
		setRowCount(rowCount);
		setRecords(records);
	}

	public void setRowCount(int rowCount) {
		this.rowCount = Integer.valueOf(rowCount);
		setPageCount(this.rowCount.intValue() % this.pageSize.intValue() == 0
				? this.rowCount.intValue() / this.pageSize.intValue()
				: this.rowCount.intValue() / this.pageSize.intValue() + 1);
	}

	public int getRowCount() {
		return this.rowCount.intValue();
	}

	public List<T> getRecords() {
		return this.records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getPageIndex() {
		return this.pageIndex.intValue();
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = Integer.valueOf(pageIndex);
	}

	public int getPageCount() {
		return this.pageCount.intValue();
	}

	public void setPageCount(int pageCount) {
		this.pageCount = Integer.valueOf(pageCount);
	}

	public int getPageSize() {
		return this.pageSize.intValue();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = Integer.valueOf(pageSize);
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
