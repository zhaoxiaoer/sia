package com.nnniu.shiro.ch2.service.impl;

import com.nnniu.shiro.ch2.dao.SessionsDao;
import com.nnniu.shiro.ch2.dao.impl.SessionsDaoImpl;
import com.nnniu.shiro.ch2.entity.Sessions;
import com.nnniu.shiro.ch2.service.SessionsService;

public class SessionsServiceImpl implements SessionsService {

	private SessionsDao sessionsDao = new SessionsDaoImpl();
	
	public Sessions createSessions(Sessions sessions) {
		return sessionsDao.createSessions(sessions);
	}
	
	public void updateSessions(Sessions sessions) {
		sessionsDao.updateSessions(sessions);
	}
	public void deleteSessions(Sessions sessions) {
		sessionsDao.deleteSessions(sessions);
	}
	
	public Sessions findOne(String sessionId) {
		return sessionsDao.findOne(sessionId);
	}
	
}
