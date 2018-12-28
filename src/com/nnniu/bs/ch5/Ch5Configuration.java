package com.nnniu.bs.ch5;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class Ch5Configuration {

	@Bean(destroyMethod="close")
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://125.46.31.185:3306/ztest");
		dataSource.setUser("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
	private Map<String, ?> jpaProperties() {
		Map<String, String> jpaPropertiesMap = new HashMap<String, String>();
		jpaPropertiesMap.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		jpaPropertiesMap.put("hibernate.hbm2ddl.auto", "update");
		return jpaPropertiesMap;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean factoryBean =
				new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.nnniu.bs.ch5", "com.nnniu.bs.ch4");
		factoryBean.setJpaPropertyMap(jpaProperties());
		return factoryBean;
	}
	
	@Bean
	public StudentDaoJpaImpl studentDao() {
		StudentDaoJpaImpl dao = new StudentDaoJpaImpl();
		return dao;
	}
	
	// 启动Spring事务
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean
	public BookDao bookDao() {
		BookDaoJpaImpl bean = new BookDaoJpaImpl();
		return bean;
	}
	
	@Bean
	public BookService bookService() {
		BookServiceImpl bean = new BookServiceImpl();
		bean.setBookDao(bookDao());
		return bean;
	}
	
	/*
	 * 启动Spring的异常管理和转换功能
	 */
	@Bean
	public static PersistenceExceptionTranslationPostProcessor 
		persistenceExceptionTranslationPostProcessor() {
		PersistenceExceptionTranslationPostProcessor bean =
				new PersistenceExceptionTranslationPostProcessor();
		return bean;
	}
}
