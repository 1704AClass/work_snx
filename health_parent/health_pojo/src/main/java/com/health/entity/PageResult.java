package com.health.entity;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable{

	private long total;
	private Object rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "PageResult [total=" + total + ", rows=" + rows + "]";
	}
	public PageResult(long total, Object rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public PageResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
