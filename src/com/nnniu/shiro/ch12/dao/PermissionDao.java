package com.nnniu.shiro.ch12.dao;

import com.nnniu.shiro.ch12.entity.Permission;

public interface PermissionDao {
	
	public Permission createPermission(Permission permission);
	public void deletePermission(Long permissionId);
}
