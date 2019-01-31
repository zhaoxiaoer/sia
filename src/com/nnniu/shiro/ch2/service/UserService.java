package com.nnniu.shiro.ch2.service;

import com.nnniu.shiro.ch2.entity.User;

public interface UserService {
	
	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	public User createUser(User user);
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
}
