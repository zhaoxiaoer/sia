package com.nnniu.shiro.ch10;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.entity.Sessions;
import com.nnniu.shiro.ch2.service.SessionsService;
import com.nnniu.shiro.ch2.service.impl.SessionsServiceImpl;

public class MySessionDAO extends CachingSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(MySessionDAO.class);
	private SessionsService sessionsService = new SessionsServiceImpl();
	
	protected Serializable doCreate(Session session) {
		logger.debug("doCreate, session: " + session.toString());
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		logger.debug("sessionId2: " + session.getId());
		sessionsService.createSessions(new Sessions(sessionId.toString(), SerializableUtils.serialize(session)));
		return session.getId();
	}
	
	protected void doUpdate(Session session) {
		logger.debug("doUpdate, session: " + session.toString());
		if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
			return; // 如果会话过期/停止，没必要再更新了
		}
		sessionsService.updateSessions(new Sessions(session.getId().toString(), SerializableUtils.serialize(session)));
	}
	
	protected void doDelete(Session session) {
		logger.debug("doDelete, session: " + session.toString());
		sessionsService.deleteSessions(new Sessions(session.getId().toString(), SerializableUtils.serialize(session)));
	}
	
	protected Session doReadSession(Serializable sessionId) {
		logger.debug("doReadSession, session: " + sessionId);
		Sessions sessions = sessionsService.findOne(sessionId.toString());
		if (sessions == null) {
			return null;
		} else {
			return SerializableUtils.deserialize(sessions.getSessionValue());
		}
	}
	
}
