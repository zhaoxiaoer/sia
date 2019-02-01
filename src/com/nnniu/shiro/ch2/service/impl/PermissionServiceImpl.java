package com.nnniu.shiro.ch2.service.impl;

import com.nnniu.shiro.ch2.dao.PermissionDao;
import com.nnniu.shiro.ch2.dao.impl.PermissionDaoImpl;
import com.nnniu.shiro.ch2.entity.Permission;
import com.nnniu.shiro.ch2.service.PermissionService;

public class PermissionServiceImpl implements PermissionService {
	
	private PermissionDao permissionDao = new PermissionDaoImpl();

	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}
	
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}
	
}
