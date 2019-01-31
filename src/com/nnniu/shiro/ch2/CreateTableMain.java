package com.nnniu.shiro.ch2;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.bh.ch3.entity.Message;
import com.nnniu.shiro.ch2.entity.User;

public class CreateTableMain {

	private static Logger logger = LoggerFactory.getLogger(CreateTableMain.class);
	
	public static void main(String[] args) {
		
		try {
			SessionFactory sessionFactory = new Configuration().
					configure("com/nnniu/shiro/ch2/hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			session.beginTransaction();
			User user = new User("zhao", "123456");
			session.save(user);
			session.getTransaction().commit();
			
			session.close();
			sessionFactory.close();
		} catch (HibernateException e) {
			logger.warn(e.toString());
		}
	}
	
}
