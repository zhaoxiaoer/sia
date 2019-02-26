package com.nnniu.shiro.ch12.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nnniu.shiro.ch12.dao.PermissionDao;
import com.nnniu.shiro.ch12.entity.Permission;

@Repository(value = "permissionDao")
public class PermissionDaoImpl implements PermissionDao {
	
	private Logger logger = LoggerFactory.getLogger(PermissionDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public Permission createPermission(Permission permission) {
		sessionFactory.getCurrentSession().save(permission);
		return permission;
	}
	
	public void deletePermission(Long permissionId) {
		Query q2 = sessionFactory.getCurrentSession().createQuery("delete from Permission where id = :id");
		q2.setParameter("id", permissionId);
		q2.executeUpdate();
	}
	
}
