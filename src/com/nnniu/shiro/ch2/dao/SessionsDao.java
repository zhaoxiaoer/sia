package com.nnniu.shiro.ch2.dao;

import com.nnniu.shiro.ch2.entity.Sessions;

public interface SessionsDao {

	public Sessions createSessions(Sessions sessions);
	public void updateSessions(Sessions sessions);
	public void deleteSessions(Sessions sessions);
	public Sessions findOne(String sessionId);
	
}
