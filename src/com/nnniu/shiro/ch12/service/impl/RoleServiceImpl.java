package com.nnniu.shiro.ch12.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnniu.shiro.ch12.dao.RoleDao;
import com.nnniu.shiro.ch12.entity.Permission;
import com.nnniu.shiro.ch12.entity.Role;
import com.nnniu.shiro.ch12.service.PermissionService;
import com.nnniu.shiro.ch12.service.RoleService;

@Service(value = "roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
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
