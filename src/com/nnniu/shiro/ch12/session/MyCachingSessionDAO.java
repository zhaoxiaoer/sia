package com.nnniu.shiro.ch12.session;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nnniu.shiro.ch12.entity.MySession;
import com.nnniu.shiro.ch12.service.MySessionService;
import com.nnniu.shiro.ch12.service.impl.SerializableUtils;

public class MyCachingSessionDAO extends CachingSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(MyCachingSessionDAO.class);
	
	@Autowired
	private MySessionService mySessionService;
	
	protected Serializable doCreate(Session session) {
		logger.debug("doCreate, session: " + session.toString());
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		logger.debug("sessionId2: " + session.getId());
		mySessionService.createMySession(new MySession(sessionId.toString(), SerializableUtils.serialize(session)));
		return session.getId();
	}
	
	protected void doUpdate(Session session) {
		logger.debug("doUpdate, session: " + session.toString());
		if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
			return; // 如果会话过期/停止，没必要再更新了
		}
		mySessionService.updateMySession(new MySession(session.getId().toString(), SerializableUtils.serialize(session)));
	}
	
	protected void doDelete(Session session) {
		logger.debug("doDelete, session: " + session.toString());
		mySessionService.deleteMySession(new MySession(session.getId().toString(), SerializableUtils.serialize(session)));
	}
	
	protected Session doReadSession(Serializable sessionId) {
		logger.debug("doReadSession, session: " + sessionId);
		MySession mySession = mySessionService.findOne(sessionId.toString());
		if (mySession == null) {
			return null;
		} else {
			return SerializableUtils.deserialize(mySession.getSessionValue());
		}
	}
	
}
