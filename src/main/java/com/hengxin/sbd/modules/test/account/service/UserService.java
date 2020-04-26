package com.hengxin.sbd.modules.test.account.service;

import java.util.List;

import com.hengxin.sbd.modules.test.account.entity.User;

public interface UserService {

	Integer insertUser(User user);

	Integer deleteUserByUserId(Integer userId);

	public Integer updateUserByUserId(User user);

	public List<User> selectAllUser();

	public User selectUserByName(String userName);
}
