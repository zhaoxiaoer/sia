package com.nnniu.bh.ch3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nnniu.bh.ch3.dao.AdvertDao;
import com.nnniu.bh.ch3.dao.CategoryDao;
import com.nnniu.bh.ch3.dao.Dao;
import com.nnniu.bh.ch3.dao.UserDao;
import com.nnniu.bh.ch3.entity.Advert;
import com.nnniu.bh.ch3.entity.Category;
import com.nnniu.bh.ch3.entity.User;

public class PostAdvertMain {
	
	private static Logger logger = LogManager.getLogger(PostAdvertMain.class);
	
	public static void main(String[] args) {
		String username = "zhao";
		String categoryTitle = "category test";
		String title = "advert test";
		String message = "advert message ...";
		
		try {
			UserDao userDao = new UserDao();
			CategoryDao categoryDao = new CategoryDao();
			AdvertDao advertDao = new AdvertDao();
			
			User user = userDao.get(username);
			Category category = categoryDao.get(categoryTitle);
			Advert advert = advertDao.create(title, message, user);
			
			category.getAdverts().add(advert);
			categoryDao.save(category);
			
			Dao.close();
		} catch (AdException e) {
			logger.error(e.getMessage());
		}
	}

}
