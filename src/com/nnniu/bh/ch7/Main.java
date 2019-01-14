package com.nnniu.bh.ch7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	
	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		logger.debug("1111111");
		SessionFactory sessionFactory = new Configuration().configure(
				"com/nnniu/bh/ch3/hibernate.cfg.xml").buildSessionFactory();
		logger.debug("222222");
	}
	
}
