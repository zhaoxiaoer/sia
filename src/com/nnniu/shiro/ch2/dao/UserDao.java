package com.nnniu.shiro.ch2.dao;

import java.util.Set;

import com.nnniu.shiro.ch2.entity.User;

public interface UserDao {
	public User createUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	
	public void correlationRoles(Long userId, Long... roleIds);
//	public void uncorrelationRoles(Long userId, Long... roleIds);
//	
	public User findOne(Long userId);
	public User findByUsername(String username);
//	public Set<String> findRoles(String username);
//	public Set<String> findPermissions(String username);
}
