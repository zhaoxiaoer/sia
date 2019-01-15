package com.nnniu.bh.ch8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nnniu.bh.ch7.entity.Picture;

public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure(
				"com/nnniu/bh/ch3/hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			// Normal session usage here...
//			Picture pic = new Picture("caption", "filename");
//			session.save(pic);
			
//			Picture pic = session.get(Picture.class, 7L, LockMode.UPGRADE_NOWAIT);
			Picture pic = session.get(Picture.class, 7L);
			logger.debug("set caption 1");
			pic.setCaption("caption 2");
			logger.debug("set caption 2");
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("set caption 3");
			
			// 虽然执行了select语句，但是没有用数据库的内容新建一个对象，pic2和pic是同一个对象
			// commit时，会出现丢失更新：该事务会覆盖另一事务已提交的更新数据
			Picture pic2 = session.get(Picture.class, 7L, LockMode.UPGRADE_NOWAIT);
			logger.debug("set caption 4: " + pic2.getCaption() + ", " + pic2.getFilename());
			logger.debug("pic: " + pic.toString() + ", pic2: " + pic2.toString());
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			logger.error("HibernateException");
			Transaction tx = session.getTransaction();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		
		sessionFactory.close();
		logger.debug("finish");
	}
	
}
