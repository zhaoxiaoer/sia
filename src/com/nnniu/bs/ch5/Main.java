package com.nnniu.bs.ch5;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("test-jpa");
		System.out.println(entityManagerFactory.isOpen());
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		// 保存
//		Student student = new Student();
//		student.setFirstName("John");
//		student.setLastName("Doe");
//		Book book1 = new Book();
//		book1.setName("Book 1");
//		Book book2 = new Book();
//		book2.setName("Book 2");
//		student.getBooks().add(book1);
//		student.getBooks().add(book2);
//		entityManager.persist(student);
		
		// 更新，删除
		Student student = entityManager.find(Student.class, 1L);
		Set<Book> books = student.getBooks();
//		System.out.println("books: " + books.size());
		Book book2 = entityManager.getReference(Book.class, 2L);
		student.setFirstName("zhao");
		entityManager.remove(book2);
//		Set<Book> books = student.getBooks();
//		System.out.println("books: " + books.size());
//		Iterator<Book> iter = books.iterator();
//		student.getBooks().remove(iter.next());
//		((Book) iter.next()).setName("update book");
		
		transaction.commit();
		
		System.out.println("2 books: " + books.size());
		
		// 使用JPA QL查询数据库
//		Query query = entityManager.createQuery(
//				"select s from Student s where s.firstName like ?1");
//		query.setParameter(1, "Jo%");
//		List<Student> students = query.getResultList();
//		Student s = students.get(0);
//		System.out.println(students.size());
//		System.out.println(s.getFirstName());
		
		entityManager.close();
	}
	
}
