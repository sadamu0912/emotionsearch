package com.xjxspace.mapper.system;

import java.util.List;
import java.util.Map;

import com.xjxspace.model.system.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	 List<User> getAllUser();
	 User getUserByUserAccount (String userAccount);
}
