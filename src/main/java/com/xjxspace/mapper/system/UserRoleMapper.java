package com.xjxspace.mapper.system;

import com.xjxspace.model.system.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    List<UserRole> getAllRecords();
    List<UserRole>  getRolesByUserId(String userId);
}
