package com.xjxspace.mapper.system;

import com.xjxspace.model.system.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {

    List<Role>  getAllRecords();

    List<Role>  getRecordsByIds(Map<String,Object> roleIds);
}
