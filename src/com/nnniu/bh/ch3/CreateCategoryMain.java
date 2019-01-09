package com.nnniu.bh.ch3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nnniu.bh.ch3.dao.CategoryDao;
import com.nnniu.bh.ch3.dao.Dao;

public class CreateCategoryMain {
	
	private static Logger logger = LogManager.getLogger(CreateCategoryMain.class);
	
	public static void main(String[] args) {
		CategoryDao categoryDao = new CategoryDao();
		String title = "category test";
		try {
			logger.debug("Creating category " + title);
			categoryDao.create(title);
			logger.debug("Created category");
			Dao.close();
		} catch (AdException e) {
			logger.error(e.getMessage());
		}
	}

}
