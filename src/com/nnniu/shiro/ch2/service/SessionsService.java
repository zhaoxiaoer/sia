package com.nnniu.shiro.ch2.service;

import com.nnniu.shiro.ch2.entity.Sessions;

public interface SessionsService {

	public Sessions createSessions(Sessions sessions);
	public void updateSessions(Sessions sessions);
	public void deleteSessions(Sessions sessions);
	public Sessions findOne(String sessionId);
	
}
