package com.nnniu.bh.ch3;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nnniu.bh.ch3.dao.CategoryDao;
import com.nnniu.bh.ch3.dao.Dao;
import com.nnniu.bh.ch3.entity.Advert;
import com.nnniu.bh.ch3.entity.Category;

public class ListAdvertsMain {
	
	private static Logger logger = LogManager.getLogger(ListAdvertsMain.class);
	
	public static void main(String[] args) {
		try {
			List<Category> categories = new CategoryDao().list();
			for (Category category : categories) {
				logger.debug("Category: " + category.getTitle());
				Set<Advert> adverts = category.getAdverts();
				for (Advert advert : adverts) {
					logger.debug("Title: " + advert.getTitle());
					logger.debug("Message: " + advert.getMessage());
					logger.debug("Posted by: " + advert.getUser().getName());
					
					logger.error("111");
				}
			}
			
			Dao.close();
		} catch (AdException e) {
			logger.error(e.getMessage());
		}
	}

}
