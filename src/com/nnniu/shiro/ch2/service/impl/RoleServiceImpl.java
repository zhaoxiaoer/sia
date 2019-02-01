package com.nnniu.shiro.ch2.service.impl;

import com.nnniu.shiro.ch2.dao.RoleDao;
import com.nnniu.shiro.ch2.dao.impl.RoleDaoImpl;
import com.nnniu.shiro.ch2.entity.Role;
import com.nnniu.shiro.ch2.service.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao = new RoleDaoImpl();
	
	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}
	
	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}
	
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}
	
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}
	
}
