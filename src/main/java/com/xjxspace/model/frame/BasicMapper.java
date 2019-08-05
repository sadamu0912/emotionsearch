package com.xjxspace.model.frame;

import java.util.List;
import java.util.Map;

public interface BasicMapper<T> {
	/**
	 * 获取所有记录
	 */
	List<T> getAllRecords(Map<String,Object> params);
	/**
	 * 获取记录总数
	 */
	Integer getRecordsCount(Map<String,Object> params);
	/**
	 * 根据条件查询数据
	 */
	List<T> getListByCondition(Map<String,Object> params);
	/**
	 * 根据条件查询分页数据
	 */
	List<T> getListwithPageInfo(Map<String,Object> params);
	/**
	 * 新增记录
	 * @param params
	 * @return
	 */
	Integer addRecord(Map<String,Object> params);
	/**
	 * 更新记录
	 */
	Integer updateRecordById(Map<String,Object> params);
	/**
	 * 更新记录 id 批量
	 */
	Integer updateRecordsByIds(Map<String,Object> params);
	/**
	 * 批量新增数据
	 */
	Integer addRecordsBatch(Map<String,Object> params);
}
