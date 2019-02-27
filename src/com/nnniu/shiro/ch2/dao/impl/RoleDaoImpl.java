package com.nnniu.shiro.ch2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.dao.RoleDao;
import com.nnniu.shiro.ch2.entity.Role;

public class RoleDaoImpl extends Dao implements RoleDao {

	private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
	
	public Role createRole(Role role) {
		try {
			begin();
			getSession().save(role);
			commit();
			return role;
		} catch (HibernateException e) {
			rollback();
			logger.error("createRole error: " + e.toString());
			return null;
		}
	}
	
	public void deleteRole(Long roleId) {
		try {
			begin();
			
			Query q = getSession().createSQLQuery("delete from link_user_role where roleid = :roleid");
			q.setParameter("roleid", roleId);
			q.executeUpdate();
			
			Query q2 = getSession().createSQLQuery("delete from link_role_permission where roleid = :roleid");
			q2.setParameter("roleid", roleId);
			q2.executeUpdate();
			
			Query q3 = getSession().createQuery("delete from Role where id = :id");
			q3.setParameter("id", roleId);
			q3.executeUpdate();
			
			commit();
		} catch (HibernateException e) {
			rollback();
			logger.error("deleteRole error: " + e.toString());
		}
	}
	
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		
		try {
			begin();
			Query q = getSession().createSQLQuery("insert into link_role_permission(roleid, permissionid) "
					+ "values (:roleid, :permissionid)");
			for (Long permissionId : permissionIds) {
				if (!exists(roleId, permissionId)) {
					q.setParameter("roleid", roleId);
					q.setParameter("permissionid", permissionId);
					q.executeUpdate();
				}
			}
			commit();
		} catch (HibernateException e) {
			rollback();
			logger.error("correlationPermissions error: " + e.toString());
		}
	}
	
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		
		try {
			begin();
			Query q = getSession().createSQLQuery("delete from link_role_permission where "
					+ "roleid = :roleid and permissionid = :permissionid");
			for (Long permissionId : permissionIds) {
				if (exists(roleId, permissionId)) {
					q.setParameter("roleid", roleId);
					q.setParameter("permissionid", permissionId);
					q.executeUpdate();
				}
			}
			commit();
		} catch (HibernateException e) {
			rollback();
			logger.error("uncorrelationPermissions error: " + e.toString());
		}
	}
	
	private boolean exists(Long roleId, Long permissionId) {
		Query q = getSession().createSQLQuery(
				"select count(1) from link_role_permission where roleid = :roleid and permissionid = :permissionid");
		q.setParameter("roleid", roleId);
		q.setParameter("permissionid", permissionId);
		List l = q.list();
		logger.debug("count: " + l.get(0));
		if (Integer.parseInt(l.get(0).toString()) > 0) {
			return true;
		}
		return false;
	}
}
