package com.leaf.mybatis.support;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pageNo = 1;

	private int pageSize = 10;
	private long count;
	private int first;
	private int last;
	private int prev;
	private int next;
	private boolean firstPage;
	private boolean lastPage;
	private int length = 10;

	private int slider = 1;

	private List<T> list = Lists.newArrayList();

	private String orderBy = "";

	private String funcName = "page";

	private String funcParam = "";

	private String message = "";

	public Page(long count, List<T> list) {
		this.count = count;
		this.list = list;
		initialize();
	}

	public Page(int pageNo, int pageSize, long count, List<T> list) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.count = count;
		this.list = list;
		initialize();
	}

	public void initialize() {
		this.first = 1;

		this.last = (int) (this.count / (this.pageSize < 1 ? 20 : this.pageSize) + this.first - 1L);

		if ((this.count % this.pageSize != 0L) || (this.last == 0)) {
			this.last += 1;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}

		if (this.pageNo <= 1) {
			this.pageNo = this.first;
			this.firstPage = true;
		}

		if (this.pageNo >= this.last) {
			this.pageNo = this.last;
			this.lastPage = true;
		}

		if (this.pageNo < this.last - 1)
			this.next = (this.pageNo + 1);
		else {
			this.next = this.last;
		}

		if (this.pageNo > 1)
			this.prev = (this.pageNo - 1);
		else {
			this.prev = this.first;
		}

		if (this.pageNo < this.first) {
			this.pageNo = this.first;
		}

		if (this.pageNo > this.last)
			this.pageNo = this.last;
	}

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

	public long getCount() {
		return this.count;
	}

	public void setCount(long count) {
		this.count = count;
		if (this.pageSize >= count)
			this.pageNo = 1;
	}

	public int getFirst() {
		return this.first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return this.last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPrev() {
		return this.prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return this.next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public boolean isFirstPage() {
		return this.firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return this.lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSlider() {
		return this.slider;
	}

	public void setSlider(int slider) {
		this.slider = slider;
	}

	public List<T> getList() {
		return this.list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncParam() {
		return this.funcParam;
	}

	public void setFuncParam(String funcParam) {
		this.funcParam = funcParam;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
