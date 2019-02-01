package com.nnniu.shiro.ch2.dao;

import com.nnniu.shiro.ch2.entity.Permission;

public interface PermissionDao {
	
	public Permission createPermission(Permission permission);
	public void deletePermission(Long permissionId);
}
