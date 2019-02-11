package com.nnniu.shiro.ch6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.dao.impl.Dao;
import com.nnniu.shiro.ch2.entity.Permission;
import com.nnniu.shiro.ch2.entity.Role;
import com.nnniu.shiro.ch2.entity.User;
import com.nnniu.shiro.ch2.service.PermissionService;
import com.nnniu.shiro.ch2.service.RoleService;
import com.nnniu.shiro.ch2.service.UserService;
import com.nnniu.shiro.ch2.service.impl.PermissionServiceImpl;
import com.nnniu.shiro.ch2.service.impl.RoleServiceImpl;
import com.nnniu.shiro.ch2.service.impl.UserServiceImpl;

public class Main2 {
	
	private static Logger logger = LoggerFactory.getLogger(Main2.class);

	public static void main(String[] args) {
		PermissionService permissionService = new PermissionServiceImpl();
		Permission p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
		Permission p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
		Permission p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
		Permission p4 = new Permission("menu:update", "菜单模块修改", Boolean.TRUE);
		permissionService.createPermission(p1);
		permissionService.createPermission(p2);
		permissionService.createPermission(p3);
		permissionService.createPermission(p4);
		logger.debug("p1: " + p1.toString());
		logger.debug("p2: " + p2.toString());
		logger.debug("p3: " + p3.toString());
		logger.debug("p4: " + p4.toString());
		permissionService.deletePermission(p4.getId());
		
		RoleService roleService = new RoleServiceImpl();
		Role r1 = new Role("admin", "超级管理员", Boolean.TRUE);
		Role r2 = new Role("user", "普通管理员", Boolean.TRUE);
		Role r3 = new Role("forum", "论坛管理员", Boolean.TRUE);
		Role r4 = new Role("picture", "图片管理员", Boolean.TRUE);
		roleService.createRole(r1);
		roleService.createRole(r2);
		roleService.createRole(r3);
		roleService.createRole(r4);
		logger.debug("r1: " + r1.toString());
		logger.debug("r2: " + r2.toString());
		logger.debug("r3: " + r3.toString());
		logger.debug("r4: " + r4.toString());
		roleService.deleteRole(r4.getId());
		
//		roleService.correlationPermissions(r1.getId(), p1.getId(), p2.getId(), p3.getId());
		roleService.correlationPermissions(r1.getId(), p1.getId(), p2.getId());
//		roleService.correlationPermissions(r2.getId(), p2.getId(), p3.getId());
//		roleService.correlationPermissions(r3.getId(), p1.getId(), p2.getId(), p3.getId());
		
		roleService.uncorrelationPermissions(r3.getId(), r1.getId(), r2.getId());
		
		UserService userService = new UserServiceImpl();
		User u1 = new User("zhao", "123");
		User u2 = new User("han", "123");
		User u3 = new User("sun", "123");
		userService.createUser(u1);
		userService.createUser(u2);
		userService.createUser(u3);
		User user = userService.findByUsername("zhao");
		logger.debug(user.toString());
		userService.changePassword(user.getId(), "123456");
		logger.debug(user.toString());
		
		userService.correlationRoles(u1.getId(), r1.getId());
		logger.debug("1: " + user.toString());
//		userService.correlationRoles(u1.getId(), r2.getId());
//		logger.debug("2: " + user.toString());
//		userService.uncorrelationRoles(u1.getId(), r2.getId());
//		logger.debug("3: " + user.toString());
		
		// 必须关闭会话再重新打开会话，否则对象不会更新
		Dao.close();
		User user2 = userService.findByUsername("zhao");
		logger.debug("4: " + user2.toString());
		
		userService.testCascade();
		
		Dao.close();
	}
	
}
