package com.nnniu.bs.ch4;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class Ch4Configuration {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://125.46.31.185:3306/ztest");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
//	@Bean(destroyMethod="close")
//	public DataSource dataSource() throws Exception {
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
//		dataSource.setJdbcUrl("jdbc:mysql://125.46.31.185:3306/ztest");
//		dataSource.setUser("root");
//		dataSource.setPassword("");
//		return dataSource;
//	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public AccountDao accountDao() {
		AccountDaoJdbcImpl accountDao = new AccountDaoJdbcImpl();
		accountDao.setJdbcTemplate(jdbcTemplate());
		return accountDao;
	}
}
