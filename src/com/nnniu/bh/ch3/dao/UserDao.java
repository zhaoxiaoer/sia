package com.nnniu.bh.ch3.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.nnniu.bh.ch3.AdException;
import com.nnniu.bh.ch3.entity.User;

public class UserDao extends Dao {

	public UserDao() {
		
	}
	
	public User get(String username) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from User where name = :username");
			q.setString("username", username);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get user " + username, e);
		}
	}
	
	public User create(String username, String password) throws AdException {
		try {
			begin();
			User user = new User(username, password);
			getSession().save(user);
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not create user " + username, e);
		}
	}
	
	public void delete(User user) throws AdException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not delete user " + user.getName(), e);
		}
	}
	
}
