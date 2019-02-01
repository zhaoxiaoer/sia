package com.nnniu.shiro.ch2.service;

import com.nnniu.shiro.ch2.entity.Permission;

public interface PermissionService {

	public Permission createPermission(Permission permission);
	public void deletePermission(Long permissionId);
	
}
