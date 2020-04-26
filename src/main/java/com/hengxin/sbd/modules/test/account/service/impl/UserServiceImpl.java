package com.hengxin.sbd.modules.test.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengxin.sbd.modules.test.account.dao.UserDao;
import com.hengxin.sbd.modules.test.account.entity.User;
import com.hengxin.sbd.modules.test.account.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public Integer insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public Integer deleteUserByUserId(Integer userId) {
		return userDao.deleteUserByUserId(userId);
	}

	@Override
	public Integer updateUserByUserId(User user) {
		return userDao.updateUserByUserId(user);
	}

	@Override
	public List<User> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public User selectUserByName(String userName) {
		return userDao.selectUserByName(userName);
	}

}
