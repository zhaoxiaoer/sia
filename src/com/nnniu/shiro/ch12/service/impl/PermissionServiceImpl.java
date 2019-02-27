package com.nnniu.shiro.ch12.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnniu.shiro.ch12.dao.PermissionDao;
import com.nnniu.shiro.ch12.entity.Permission;
import com.nnniu.shiro.ch12.service.PermissionService;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	@Autowired
	private PermissionDao permissionDao;
	
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}
	
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}
	
}
