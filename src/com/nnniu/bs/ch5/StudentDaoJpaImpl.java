package com.nnniu.bs.ch5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentDaoJpaImpl {

	@PersistenceUnit
//	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public void save(Student student) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(student);
		
		entityTransaction.commit();
		entityManager.close();
	}
	
}
