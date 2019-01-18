package com.nnniu.bh.ch9;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.nnniu.bh.ch7.entity.Picture;
import com.nnniu.bh.ch9.entity.Product;
import com.nnniu.bh.ch9.entity.Software;
import com.nnniu.bh.ch9.entity.Supplier;

public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure(
				"com/nnniu/bh/ch3/hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			// Normal session usage here...
			// 插入数据
//			Supplier supplier = new Supplier("Hardware, Inc.");
//			supplier.getProducts().add(
//					new Product(supplier, "Optical Wheel Mouse", "Mouse", 5.00));
//			supplier.getProducts().add(
//					new Product(supplier, "Trackball Mouse", "Mouse", 22.00));
//			session.save(supplier);
//			
//			supplier = new Supplier("Supplier 2");
//			supplier.getProducts().add(
//					new Software(supplier, "SuperDetect", "Antivirus", 14.95, "1.0"));
//			supplier.getProducts().add(
//					new Software(supplier, "Wildcat", "Browser", 19.95, "2.2"));
//			supplier.getProducts().add(
//					new Product(supplier, "AxeGrinder", "Gaming Mouse", 42.00));
//			session.save(supplier);
			
			// 测试"命名查询"
//			Query query = session.getNamedQuery("supplier.findAll");
//			List<Supplier> suppliers = query.list();
//			for (Supplier supplier : suppliers) {
//				logger.debug(supplier.toString());
//			}
			
			// 测试"带参数的命名查询"
//			Query query = session.getNamedQuery("supplier.findByName");
//			query.setParameter("name", "Hardware, Inc.");
//			Supplier supplier = (Supplier) query.uniqueResult();
//			logger.debug(supplier.toString());
			
			// 测试"原始 sql 命名查询"
//			Query query = session.getNamedQuery("supplier.findAverage");
//			List<Object[]> suppliers = query.list();
//			for (Object[] o : suppliers) {
//				logger.debug(Arrays.toString(o));
//			}
			
			// 测试join(联结表)
//			Query query = session.getNamedQuery("product.findProductAndSupplier");
//			List<Object[]> suppliers = query.list();
//			for (Object[] o : suppliers) {
//				logger.debug(Arrays.toString(o));
//			}
			
//			Query query = session.createQuery("from Supplier");
//			List<Supplier> suppliers = query.list();
//			for (Supplier supplier : suppliers) {
//				logger.debug(supplier.toString());
//			}
			
//			Query query = session.createQuery("select p.name, p.price from Product p "
//					+ "where p.price > 15.0 and p.name like 'Wild%'");
//			List<Object[]> os = query.list();
//			for (Object[] o : os) {
//				logger.debug(Arrays.toString(o));
//			}
			
//			Query query = session.createQuery("from Product p "
//					+ "where p.price > 15.0 and p.name like 'Wild%'");
//			List<Product> products = query.list();
//			for (Product product : products) {
//				logger.debug(product.toString());
//				product.setPrice(100.0);
//			}
			
			// 测试结果集分页
//			Query query = session.createQuery("from Product p order by p.id desc");
//			query.setFirstResult(2);
//			query.setMaxResults(2);
//			List<Product> products = query.list();
//			for (Product product : products) {
//				logger.debug(product.toString());
//			}
			
			// 测试联结
//			Query query = session.createQuery("from Product p inner join p.supplier s");
//			List<Object[]> objs = query.list();
//			for (Object[] obj : objs) {
//				logger.debug(Arrays.toString(obj));
//			}
			
			// 测试HQL统计方法
//			Query query = session.createQuery("select count(*) from User u inner join Picture as p on u.id = p.id");
//			Long count = (Long) query.uniqueResult();
//			logger.debug("count: " + count);
//			Query query = session.createQuery("select count(distinct p.filename) from Picture p");
//			Query query = session.createQuery("select count(distinct p.caption) from Picture p");
//			Query query = session.createQuery("select avg(p.price) from Product p");
//			Query query = session.createQuery("select max(p.price) from Product p");
//			Query query = session.createQuery("select min(p.price) from Product p");
//			Query query = session.createQuery("select sum(p.price) from Product p");
//			Double count = (Double) query.uniqueResult();
//			logger.debug("count: " + count);
			
			// 测试过滤器
			Query query = session.createQuery("from Product p");
			Filter filter = session.enableFilter("maxIdFilter");
			filter.setParameter("maxIdParam", 3L);
			List<Product> products = query.list();
			for (Product product : products) {
				logger.debug(product.toString());
			}
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			logger.error("HibernateException");
			Transaction tx = session.getTransaction();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			session.close();
			sessionFactory.close();
		}
		
		logger.debug("finish");
	}
	
}
