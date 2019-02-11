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
	 * 修改密码
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(Long userId, String newPassword);
	
	/**
	 * 添加用户-角色关系
	 * @param userId
	 * @param roleIds
	 */
	public void correlationRoles(Long userId, Long... roleIds);
	
	/**
	 * 删除用户-角色关系
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds);
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	
	// 测试
	public void testCascade();
}
