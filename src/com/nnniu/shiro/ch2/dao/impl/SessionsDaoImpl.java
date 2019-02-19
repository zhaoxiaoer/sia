package com.nnniu.shiro.ch2.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.dao.SessionsDao;
import com.nnniu.shiro.ch2.entity.Sessions;
import com.nnniu.shiro.ch2.entity.User;

public class SessionsDaoImpl extends Dao implements SessionsDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public Sessions createSessions(Sessions sessions) {
		try {
			begin();
			Dao.getSession().save(sessions);
			commit();
			Dao.close();
			return sessions;
		} catch (HibernateException e) {
			rollback();
			logger.error("createSession error: " + e.toString());
			return null;
		}
	}

	@Override
	public void updateSessions(Sessions sessions) {
		try {
			begin();
			Query q = getSession().createQuery("from Sessions where sessionId = :sessionId");
			q.setParameter("sessionId", sessions.getSessionId());
			Sessions sess = (Sessions) q.uniqueResult();
			sess.setSessionValue(sessions.getSessionValue());
			commit();
			Dao.close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
			logger.error("updateSession error: " + e.toString());
		}
	}

	@Override
	public void deleteSessions(Sessions sessions) {
		try {
			begin();
			Query q = getSession().createQuery("delete from Sessions where sessionId = :sessionId");
			q.setParameter("sessionId", sessions.getSessionId());
			q.executeUpdate();
			commit();
			Dao.close();
		} catch (HibernateException e) {
			rollback();
			logger.error("deleteSession error: " + e.toString());
		}
	}

	@Override
	public Sessions findOne(String sessionId) {
		try {
			begin();
			Query q = getSession().createQuery("from Sessions where sessionId = :sessionId");
			q.setParameter("sessionId", sessionId);
			Sessions sess = (Sessions) q.uniqueResult();
			commit();
			Dao.close();
			return sess;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
			logger.error("findOne error: " + e.toString());
			return null;
		}
	}

}
