package com.nnniu.shiro.ch12.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnniu.shiro.ch12.dao.PermissionDao;
import com.nnniu.shiro.ch12.dao.impl.PermissionDaoImpl;
import com.nnniu.shiro.ch12.entity.Permission;
import com.nnniu.shiro.ch12.service.PermissionService;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	@Autowired
	private PermissionDao permissionDao;
	
	private int a;

	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}
	
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}
	
	public void test() {
		if (a == 0) {
			Permission p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
			createPermission(p2);
			logger.debug("a = 0");
			a++;
		} else if (a == 1) {
			Permission p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
			Permission p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
			createPermission(p1);
			createPermission(p2);
			logger.debug("a = 0");
			a++;
		}
	}
	
}
