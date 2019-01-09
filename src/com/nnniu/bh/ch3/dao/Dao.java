package com.nnniu.bh.ch3.dao;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Dao {
	
	private static final Logger logger = LogManager.getLogger(Dao.class);
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static final SessionFactory sessionFactory = new Configuration()
			.configure("com/nnniu/bh/ch3/hibernate.cfg.xml").buildSessionFactory();
	
	protected Dao() {
		
	}
	
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
