package com.nnniu.shiro.ch12.service;

import com.nnniu.shiro.ch12.entity.Role;

public interface RoleService {
	
	public Role createRole(Role role);
	public void deleteRole(Long roleId);
	public void correlationPermissions(Long roleId, Long... permissionIds);
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);
		
}
