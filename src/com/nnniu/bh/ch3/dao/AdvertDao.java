package com.nnniu.bh.ch3.dao;

import org.hibernate.HibernateException;

import com.nnniu.bh.ch3.AdException;
import com.nnniu.bh.ch3.entity.Advert;
import com.nnniu.bh.ch3.entity.User;

public class AdvertDao extends Dao {

	public Advert create(String title, String message, User user) throws AdException {
		try {
			begin();
			Advert advert = new Advert(title, message, user);
			getSession().save(advert);
			commit();
			return advert;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not create advert", e);
		}
	}
	
	public void delete(Advert advert) throws AdException {
		try {
			begin();
			getSession().delete(advert);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not delete advert", e);
		}
	}
	
}
