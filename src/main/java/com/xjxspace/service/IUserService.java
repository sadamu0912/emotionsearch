package com.xjxspace.service;


import com.xjxspace.model.system.User;

public interface IUserService {

    User findByUserAccount(String userAccout);

    User findUserByTelephone(String telephone);

    User addUserByPhone(String telephone);


}
