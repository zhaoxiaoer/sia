package com.nnniu.bh.ch3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nnniu.bh.ch3.dao.Dao;
import com.nnniu.bh.ch3.dao.UserDao;

public class CreateUserMain {
	
	private static Logger logger = LogManager.getLogger(CreateUserMain.class);

	public static void main(String[] args) {
		String username = "zhao";
		String password = "123456";
		
		try {
			UserDao userDao = new UserDao();
			logger.debug("Creating user " + username);
			userDao.create(username, password);
			logger.debug("Created user");
			Dao.close();
		} catch (AdException e) {
			logger.error(e.getMessage());
		}
	}
	
}
