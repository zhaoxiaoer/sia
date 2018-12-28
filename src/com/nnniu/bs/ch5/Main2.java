package com.nnniu.bs.ch5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

public class Main2 {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ch5Configuration.class);
		
//		EntityManagerFactory entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class);
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction entityTransaction = entityManager.getTransaction();
//		entityTransaction.begin();
//		Student student = new Student();
//		student.setFirstName("zhao");
//		student.setLastName("xiaoer");
//		Book book1 = new Book();
//		book1.setName("book3");
//		Book book2 = new Book();
//		book2.setName("book4");
//		student.getBooks().add(book1);
//		student.getBooks().add(book2);
//		entityManager.persist(student);
//		entityTransaction.commit();
//		entityManager.close();
		
//		StudentDaoJpaImpl dao = applicationContext.getBean(StudentDaoJpaImpl.class);
//		Student student = new Student();
//		student.setFirstName("zhao1");
//		student.setLastName("xiaoer1");
//		dao.save(student);
		
		BookService bookService = applicationContext.getBean(BookService.class);
		Book book = new Book();
		book.setName("book aaa");
		try {
			bookService.save(book);
		} catch (DataAccessException e) {
			System.out.println("myy exception");
			e.printStackTrace();
		}
	}

}
