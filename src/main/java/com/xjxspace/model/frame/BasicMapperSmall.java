package com.xjxspace.model.frame;

import java.util.List;
import java.util.Map;

public interface BasicMapperSmall<T>  {
	/**
	 * 获取所有记录
	 * @param params
	 * @return
	 */
	List<T> getAllRecords(Map<String,Object> params);
	/**
	 * 
	 * @param params
	 * @return
	 */
	List<T> getListByCondition(Map<String,Object> params);
	/**
	 * 带有分页信息的数据  
	 * @param params
	 * @return
	 */
	List<T> getListwithPageInfo(Map<String,Object> params);
	/**
	 * 获取数据总条数
	 * @param params
	 * @return
	 */
	Integer getRecordsCount(Map<String,Object> params);
	
	/**
	 * 新增
	 */
	Integer addRecord(Map<String,Object> params);
	
	
	Integer updateRecordById(Map<String,Object> params);
}
