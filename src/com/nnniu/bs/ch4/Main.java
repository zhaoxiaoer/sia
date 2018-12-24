package com.nnniu.bs.ch4;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
		
		// 调试 Spring JDBC
		Account account = accountDao.find(100L);
		System.out.println(account);
		
		Account account2 = accountDao.find("john doe").get(0);
		System.out.println(account2);
		
		Account account3 = new Account();
		account3.setOwnerName("zhaoxiaoer");
		account3.setBalance(100);
		account3.setAccessTime(new Date());
		account3.setLocked(false);
		
		accountDao.insert(account3);
		
		Account account4 = accountDao.find(account3.getId());
		System.out.println("account4: " + account4);
		
		account4.setBalance(101.1);
		accountDao.update(account4);
		
		account4 = accountDao.find(account4.getId());
		System.out.println("account4: " + account4);
		
		accountDao.delete(account4.getId());
		List<Account> accounts = accountDao.find(Arrays.asList(account.getId()));
		System.out.println(accounts.size());
	}
	
}
