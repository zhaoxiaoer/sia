package com.nnniu.shiro.ch6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.entity.User;
import com.nnniu.shiro.ch2.service.UserService;
import com.nnniu.shiro.ch2.service.impl.UserServiceImpl;

public class Main2 {
	
	private static Logger logger = LoggerFactory.getLogger(Main2.class);

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		userService.createUser(new User("zhao", "123"));
		userService.createUser(new User("han", "123"));
		userService.createUser(new User("sun", "123"));
		User u = userService.findByUsername("zhao");
		logger.debug(u.toString());
	}
	
}
