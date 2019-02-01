package com.nnniu.shiro.ch2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.dao.MyGlobalException;
import com.nnniu.shiro.ch2.dao.UserDao;
import com.nnniu.shiro.ch2.entity.User;

public class UserDaoImpl extends Dao implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User createUser(User user) {
		try {
			begin();
			getSession().save(user);
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
//			throw new MyGlobalException("Cannot create user " + user.getUsername(), e);
			logger.error("createUser error: " + e.toString());
			return null;
		}
	}
	
	public void updateUser(User user) {
		try {
			begin();
			getSession().update(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			logger.error("updateUser error: " + e.toString());
		}
	}
	
	public void deleteUser(User user) {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			logger.error("deleteUser error: " + e.toString());
		}
	}
	
	public void correlationRoles(Long userId, Long... roleIds) {
		if (roleIds == null || roleIds.length == 0) {
			return;
		}
		
		try {
			begin();
//			Query q = getSession().createSQLQuery("insert into link_user_role(userid, roleid) "
//					+ "values (:userid, :roleid)");
//			for (Long roleId : roleIds) {
//				if (!exists(userId, roleId)) {
//					q.setParameter("userid", userId);
//					q.setParameter("roleid", roleId);
//					q.executeUpdate();
//				}
//			}
		
			Query q = getSession().createSQLQuery("insert into link_user_role(userid, roleid) "
					+ "values (:userid, :roleid)");
			for (Long roleId : roleIds) {
				if (!exists(userId, roleId)) {
					logger.debug("11111111111111111111");
					q.setParameter("userid", userId);
					q.setParameter("roleid", roleId);
					q.executeUpdate();
				}
			}
			
			commit();
		} catch (HibernateException e) {
			rollback();
			logger.error("correlationRoles error: " + e.toString());
		}
	}
	
	private boolean exists(Long userId, Long roleId) {
		Query q = getSession().createSQLQuery("select count(1) from link_user_role "
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
		try {
			begin();
			Query q = getSession().createQuery("from User where id = :id");
			q.setParameter("id", id);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			logger.error("findOne error: " + e.toString());
			return null;
		}
	}
	
	public User findByUsername(String username) {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setParameter("username", username);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			logger.error("FindByUsername error: " + e.toString());
			return null;
		}
	}
	
}
