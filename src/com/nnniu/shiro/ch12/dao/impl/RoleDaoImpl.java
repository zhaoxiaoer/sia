package com.nnniu.shiro.ch12.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nnniu.shiro.ch12.dao.RoleDao;
import com.nnniu.shiro.ch12.entity.Role;

@Repository(value = "roleDao")
public class RoleDaoImpl implements RoleDao {

	private Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Role createRole(Role role) {
		sessionFactory.getCurrentSession().save(role);
		return role;
	}
	
	public void deleteRole(Long roleId) {
		Session session = sessionFactory.getCurrentSession();
		
		Query q = session.createSQLQuery("delete from link_user_role where roleid = :roleid");
		q.setParameter("roleid", roleId);
		q.executeUpdate();
		
		Query q2 = session.createSQLQuery("delete from link_role_permission where roleid = :roleid");
		q2.setParameter("roleid", roleId);
		q2.executeUpdate();
		
		Query q3 = session.createSQLQuery("delete from roles where id = :id");
		q3.setParameter("id", roleId);
		q3.executeUpdate();
		
		// 该HQL会生成两条SQL语句
//		Query q3 = session.createQuery("delete from Role where id = :id");
//		q3.setParameter("id", roleId);
//		q3.executeUpdate();
	}
	
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery("insert into link_role_permission(roleid, permissionid) "
				+ "values (:roleid, :permissionid)");
		for (Long permissionId : permissionIds) {
			if (!exists(roleId, permissionId)) {
				q.setParameter("roleid", roleId);
				q.setParameter("permissionid", permissionId);
				q.executeUpdate();
			}
		}
	}
	
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery("delete from link_role_permission where "
				+ "roleid = :roleid and permissionid = :permissionid");
		for (Long permissionId : permissionIds) {
			if (exists(roleId, permissionId)) {
				q.setParameter("roleid", roleId);
				q.setParameter("permissionid", permissionId);
				q.executeUpdate();
			}
		}
	}
	
	private boolean exists(Long roleId, Long permissionId) {
		Query q = sessionFactory.getCurrentSession().createSQLQuery(
				"select count(1) from link_role_permission where roleid = :roleid and permissionid = :permissionid");
		q.setParameter("roleid", roleId);
		q.setParameter("permissionid", permissionId);
		List l = q.list();
//		logger.debug("count: " + l.get(0));
		if (Integer.parseInt(l.get(0).toString()) > 0) {
			return true;
		}
		return false;
	}
}
