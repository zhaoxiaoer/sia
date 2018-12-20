package com.nnniu.bs.ch4;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main {

	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(Ch4Configuration.class);
		DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
		long time = System.currentTimeMillis();
		Connection connection = dataSource.getConnection();
		time = System.currentTimeMillis() - time;
		System.out.println("Connection time: " + time + "ms");
		System.out.println(connection.isClosed());
		connection.close();
		System.out.println(connection.isClosed());
		
		System.out.println("------------");
		
		time = System.currentTimeMillis();
		connection = dataSource.getConnection();
		time = System.currentTimeMillis() - time;
		System.out.println("Connection time: " + time + "ms");
		System.out.println(connection.isClosed());
		connection.close();
		System.out.println(connection.isClosed());
		
		System.out.println("************");
		
		time = System.currentTimeMillis();
		connection = dataSource.getConnection();
		time = System.currentTimeMillis() - time;
		System.out.println("Connection time: " + time + "ms");
		System.out.println(connection.isClosed());
		connection.close();
		System.out.println(connection.isClosed());
		
		AccountDaoJdbcImpl accountDao = applicationContext.getBean("accountDao", AccountDaoJdbcImpl.class);
		System.out.println("accountDao: " + accountDao);
		
		JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
		System.out.println("accountDao.jdbcTemplate: " + accountDao.getJdbcTemplate() + ", jdbcTemplate: " + jdbcTemplate);
		
		System.out.println("dataSource: " + dataSource + ", jdbcTemplate.getDataSource: "  + jdbcTemplate.getDataSource());
	}
	
}
