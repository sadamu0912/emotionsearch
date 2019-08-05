package com.xjxspace.model.frame;

import java.util.Map;

public class ControllerHandler {
	/**
	*获取分页对象
	*/
	public Pagination getPaginationInfo(Map<String, Object> params, Integer count) {
	Integer page, offset, start, total;
	if (params.containsKey("page")) {
	page = Integer.valueOf(params. get("page").toString());
	page = page < 1 ? 1 : page;
	} else {
	page = 1;
	}
	if (params.containsKey("offset")) {
	offset = Integer.valueOf(params. get("offset").toString());
	offset = offset < 1 ? 10 : offset;
	} else {
	offset = 10;
	}
	if (count  % offset == 0) {
	total = count / offset;
	} else {
	total = (count / offset) + 1;
	}
	page = page > total ? 1 : page;
	start = (page - 1) * offset;
	return new Pagination(start, offset, page, total, count);
	}
}
