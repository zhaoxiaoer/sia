package com.nnniu.shiro.ch12.service;

import com.nnniu.shiro.ch12.entity.Permission;

public interface PermissionService {

	public Permission createPermission(Permission permission);
	public void deletePermission(Long permissionId);
	
	public void test();
	
}
