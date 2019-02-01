package com.nnniu.shiro.ch2.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.dao.PermissionDao;
import com.nnniu.shiro.ch2.entity.Permission;

public class PermissionDaoImpl extends Dao implements PermissionDao {
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionDaoImpl.class);

	public Permission createPermission(Permission permission) {
		try {
			begin();
			getSession().save(permission);
			commit();
			return permission;
		} catch (HibernateException e) {
			rollback();
			logger.error("createPermission error: " + e.toString());
			return null;
		}
	}
	
	public void deletePermission(Long permissionId) {
		try {
			begin();
			// 手动删除链接表中的相关数据
			Query q = getSession().createSQLQuery("delete from link_role_permission where permissionid = :permissionid");
			q.setParameter("permissionid", permissionId);
			q.executeUpdate();
			
			Query q2 = getSession().createQuery("delete from Permission where id = :id");
			q2.setParameter("id", permissionId);
			q2.executeUpdate();
			commit();
		} catch (HibernateException e) {
			rollback();
			logger.error("deletePermission error: " + e.toString());
		}
	}
	
}
