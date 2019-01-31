package com.nnniu.shiro.ch2.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.dao.MyGlobalException;
import com.nnniu.shiro.ch2.dao.UserDao;
import com.nnniu.shiro.ch2.entity.User;

public class UserDaoImpl extends Dao implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(Dao.class);
	
	public User createUser(User user) {
		try {
			begin();
			getSession().save(user);
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
//			throw new MyGlobalException("Cannot create user " + user.getUsername(), e);
			logger.error("CreateUser error: " + e.toString());
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
			logger.error("UpdateUser error: " + e.toString());
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
