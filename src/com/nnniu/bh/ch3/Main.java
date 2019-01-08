package com.nnniu.bh.ch3;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
	
	private static Logger logger = LogManager.getLogger(Main.class);
	
	
	public static void main(String[] args) {		
		SessionFactory sessionFactory = new Configuration().
				configure("com/nnniu/bh/ch3/hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Message msg = new Message("Hibernated a message on " + new Date());
		session.save(msg);
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		List messages = session.createQuery("from Message").list();
		logger.debug("Found " + messages.size() + "message(s):");
		Iterator i = messages.iterator();
		while (i.hasNext()) {
			Message msg2 = (Message) i.next();
			logger.debug(msg2.getMessage());
		}
		session.close();
	}
	
}
