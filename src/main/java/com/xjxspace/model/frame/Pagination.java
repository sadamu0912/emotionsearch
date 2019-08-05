package com.xjxspace.model.frame;

import java.io.Serializable;

public class Pagination implements Serializable {
	private static final long  seriatVersionUID = 2648333339102999289L;
	public static final String className="pageInfo";
	private Integer start;
	private Integer offset;
	private Integer current;
	private Integer total;
	private Integer count;
	public Pagination(Integer start, Integer offset) {
		this.start = start;
		this.offset = offset;
	}
	public Pagination(Integer start, Integer offset, Integer current, Integer total,Integer count) {
		super();
		this.start = start;
		this.offset = offset;
		this.current = current;
		this.total = total;
		this.setCount(count);
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}