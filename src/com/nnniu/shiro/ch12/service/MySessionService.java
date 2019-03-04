package com.nnniu.shiro.ch12.service;

import com.nnniu.shiro.ch12.entity.MySession;

public interface MySessionService {

	public MySession createMySession(MySession mySession);
	public void updateMySession(MySession mySession);
	public void deleteMySession(MySession mySession);
	public MySession findOne(String sessionId);
	
}
