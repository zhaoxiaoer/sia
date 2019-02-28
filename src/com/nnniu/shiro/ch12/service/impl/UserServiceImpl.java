package com.nnniu.shiro.ch12.service.impl;

import java.util.Set;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnniu.shiro.ch12.dao.UserDao;
import com.nnniu.shiro.ch12.entity.Permission;
import com.nnniu.shiro.ch12.entity.Role;
import com.nnniu.shiro.ch12.entity.User;
import com.nnniu.shiro.ch12.service.PermissionService;
import com.nnniu.shiro.ch12.service.RoleService;
import com.nnniu.shiro.ch12.service.UserService;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	public User createUser(User user) {
		Hash hash = ((DefaultPasswordService) passwordService).hashPassword(user.getPassword());
		user.setSalt(hash.getSalt().toHex());
		user.setPassword(hash.toHex());
		return userDao.createUser(user);
	}
	
	public void changePassword(Long id, String newPassword) {
		User user = userDao.findOne(id);
		if (user == null) {
			return;
		}
		
		Hash hash = ((DefaultPasswordService) passwordService).hashPassword(newPassword);
		user.setSalt(hash.getSalt().toHex());
		user.setPassword(hash.toHex());
		userDao.updateUser(user);
	}
	
	public void correlationRoles(Long userId, Long... roleIds) {
		userDao.correlationRoles(userId, roleIds);
	}
	
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDao.uncorrelationRoles(userId, roleIds);
	}
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public Set<String> findRoles(String username) {
		return userDao.findRoles(username);
	}
	
	public Set<String> findPermissions(String username) {
		return userDao.findPermissions(username);
	}
	
	// 测试
	public void test() {
		Permission p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
		Permission p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
		Permission p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
		Permission p4 = new Permission("menu:update", "菜单模块修改", Boolean.TRUE);
		permissionService.createPermission(p1);
		permissionService.createPermission(p2);
		permissionService.createPermission(p3);
		permissionService.createPermission(p4);
		permissionService.deletePermission(p4.getId());
		
		Role r1 = new Role("admin", "超级管理员", Boolean.TRUE);
		Role r2 = new Role("user", "普通管理员", Boolean.TRUE);
		Role r3 = new Role("forum", "论坛管理员", Boolean.TRUE);
		Role r4 = new Role("picture", "图片管理员", Boolean.TRUE);
		roleService.createRole(r1);
		roleService.createRole(r2);
		roleService.createRole(r3);
		roleService.createRole(r4);
		roleService.deleteRole(r4.getId());
		
		roleService.correlationPermissions(r1.getId(), p1.getId(), p2.getId(), p3.getId());
		roleService.correlationPermissions(r2.getId(), p2.getId(), p3.getId());
		roleService.correlationPermissions(r3.getId(), p2.getId(), p3.getId());
		roleService.uncorrelationPermissions(r2.getId(), p3.getId());
		roleService.deleteRole(r3.getId());
		
		User u1 = new User("zhao", "123");
		User u2 = new User("han", "123");
		User u3 = new User("sun", "123");
		createUser(u1);
		createUser(u2);
		createUser(u3);
		User user = findByUsername("zhao");
		logger.debug(user.toString());
		changePassword(user.getId(), "123456");
		logger.debug(user.toString());
		
		correlationRoles(u1.getId(), r1.getId());
		logger.debug("1: " + user.toString());
		correlationRoles(u1.getId(), r2.getId());
		logger.debug("2: " + user.toString());
		uncorrelationRoles(u1.getId(), r2.getId());
		logger.debug("3: " + user.toString());
	}
	
}
