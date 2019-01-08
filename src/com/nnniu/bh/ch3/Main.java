package com.nnniu.bh.ch3;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
	
	private static Logger logger = LogManager.getLogger(Main.class);
	
	
	public static void main(String[] args) {
		logger.error("1111111");
		
		SessionFactory sessionFactory = new Configuration().
				configure("com/nnniu/bh/ch3/hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Message msg = new Message("Hibernated a message on " + new Date());
		session.save(msg);
		session.getTransaction().commit();
		session.close();
	}
	
}
