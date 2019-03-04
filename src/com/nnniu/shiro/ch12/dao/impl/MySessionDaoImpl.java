package com.nnniu.shiro.ch12.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nnniu.shiro.ch12.dao.MySessionDao;
import com.nnniu.shiro.ch12.entity.MySession;

@Repository(value = "mySessionDao")
public class MySessionDaoImpl implements MySessionDao {
	
	private static final Logger logger = LoggerFactory.getLogger(MySessionDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public MySession createMySession(MySession mySession) {
		sessionFactory.getCurrentSession().save(mySession);
		return mySession;
	}

	@Override
	public void updateMySession(MySession mySession) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from MySession where sessionId = :sessionId");
		q.setParameter("sessionId", mySession.getSessionId());
		MySession mySession2 = (MySession) q.uniqueResult();
		mySession2.setSessionValue(mySession.getSessionValue());
	}

	@Override
	public void deleteMySession(MySession mySession) {
		Query q = sessionFactory.getCurrentSession().createQuery("delete from MySession where sessionId = :sessionId");
		q.setParameter("sessionId", mySession.getSessionId());
		q.executeUpdate();
	}

	@Override
	public MySession findOne(String sessionId) {
		Query q = sessionFactory.getCurrentSession().createQuery("from MySession where sessionId = :sessionId");
		q.setParameter("sessionId", sessionId);
		MySession mySession = (MySession) q.uniqueResult();
		return mySession;
	}

}
