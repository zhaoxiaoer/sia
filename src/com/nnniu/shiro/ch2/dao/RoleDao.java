package com.nnniu.shiro.ch2.dao;

import com.nnniu.shiro.ch2.entity.Role;

public interface RoleDao {

	public Role createRole(Role role);
	public void deleteRole(Long roleId);
	
	public void correlationPermissions(Long roleId, Long... permissionIds);
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);
	
}