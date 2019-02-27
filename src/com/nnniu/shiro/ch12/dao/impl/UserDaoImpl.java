package com.nnniu.shiro.ch12.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nnniu.shiro.ch12.dao.UserDao;
import com.nnniu.shiro.ch12.entity.Permission;
import com.nnniu.shiro.ch12.entity.Role;
import com.nnniu.shiro.ch12.entity.User;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public User createUser(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user;
	}
	
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}
	
	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}
	
	public void correlationRoles(Long userId, Long... roleIds) {
		if (roleIds == null || roleIds.length == 0) {
			return;
		}
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery("insert into link_user_role(userid, roleid) "
				+ "values (:userid, :roleid)");
		for (Long roleId : roleIds) {
			if (!exists(userId, roleId)) {
				q.setParameter("userid", userId);
				q.setParameter("roleid", roleId);
				q.executeUpdate();
			}
		}
	}
	
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		if (roleIds == null || roleIds.length == 0) {
			return;
		}
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery("delete from link_user_role "
				+ "where userid = :userid and roleid = :roleid");
		for (Long roleId : roleIds) {
			if (exists(userId, roleId)) {
				q.setParameter("userid", userId);
				q.setParameter("roleid", roleId);
				q.executeUpdate();
			}
		}
	}
	
	private boolean exists(Long userId, Long roleId) {
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select count(1) from link_user_role "
				+ "where userid = :userid and roleid = :roleid");
		q.setParameter("userid", userId);
		q.setParameter("roleid", roleId);
		List l = q.list();
		if (Integer.parseInt(l.get(0).toString()) > 0) {
			return true;
		}
		
		return false;
	}
	
	
	public User findOne(Long id) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User where id = :id");
		q.setParameter("id", id);
		User user = (User) q.uniqueResult();
		return user;
	}
	
	public User findByUsername(String username) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
		q.setParameter("username", username);
		User user = (User) q.uniqueResult();
		return user;
	}
	
	public Set<String> findRoles(String username) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
		q.setParameter("username", username);
		User user = (User) q.uniqueResult();
		Set<Role> roles = user.getRoles();
		HashSet<String> rolesStr = new HashSet<String>();
		for (Role role : roles) {
			rolesStr.add(role.getRole());
		}
		return rolesStr;
	}
	
	public Set<String> findPermissions(String username) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
		q.setParameter("username", username);
		User user = (User) q.uniqueResult();
		Set<Role> roles = user.getRoles();
		HashSet<String> permissionsStr = new HashSet<String>();
		for (Role role : roles) {
			Set<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				permissionsStr.add(permission.getPermission());
			}
		}
		return permissionsStr;
	}
	
}
