package com.nnniu.shiro.ch2.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dao {

	private static final Logger logger = LoggerFactory.getLogger(Dao.class);
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static final SessionFactory sessionFactory = new Configuration()
			.configure("com/nnniu/shiro/ch2/hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() {
		Session session = (Session) Dao.session.get();
		if (session == null) {
			session = sessionFactory.openSession();
			Dao.session.set(session);
		}
		return session;
	}
	
	protected void begin() {
		getSession().beginTransaction();
	}
	
	protected void commit() {
		getSession().getTransaction().commit();
	}
	
	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			logger.error("Cannot rollback: " + e.toString());
		}
		
		try {
			getSession().close();
		} catch (HibernateException e) {
			logger.error("Cannot close: " + e.toString());
		}
		
		Dao.session.set(null);
	}
	
	public static void close() {
		getSession().close();
		Dao.session.set(null);
	}
}
