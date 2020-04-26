package com.hengxin.sbd.modules.test.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hengxin.sbd.modules.test.account.entity.User;
import com.hengxin.sbd.modules.test.account.service.UserService;

@RestController
@RequestMapping("/userController")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(value = "/user", consumes = "application/json")
	public int insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	@DeleteMapping("/user/{userId}")
	public int deleteUserByUserId(@PathVariable int userId) {
		return userService.deleteUserByUserId(userId);
	}

	@PutMapping(value = "/user", consumes = "application/x-www-form-urlencoded")
	public int updateUserByUserId(@ModelAttribute User user) {
		return userService.updateUserByUserId(user);
	}
	
	@GetMapping("/users")
	public List<User> selectAllUser() {
		return userService.selectAllUser();
	}
	
	@RequestMapping("/user")
	public User selectUserByName(@RequestParam String userName) {
		return userService.selectUserByName(userName);
	}
}
