package com.nnniu.shiro.ch12.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnniu.shiro.ch12.dao.MySessionDao;
import com.nnniu.shiro.ch12.entity.MySession;
import com.nnniu.shiro.ch12.service.MySessionService;

@Service(value = "mySessionService")
@Transactional
public class MySessionServiceImpl implements MySessionService {

	@Autowired
	private MySessionDao mySessionDao;
	
	public MySession createMySession(MySession mySession) {
		return mySessionDao.createMySession(mySession);
	}
	
	public void updateMySession(MySession mySession) {
		mySessionDao.updateMySession(mySession);
	}
	
	public void deleteMySession(MySession mySession) {
		mySessionDao.deleteMySession(mySession);
	}
	
	public MySession findOne(String sessionId) {
		return mySessionDao.findOne(sessionId);
	}
	
}
