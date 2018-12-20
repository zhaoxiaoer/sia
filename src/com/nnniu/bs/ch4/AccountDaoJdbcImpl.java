package com.nnniu.bs.ch4;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDaoJdbcImpl implements AccountDao {
	
	private JdbcTemplate jdbcTemplate;
	
	// getters & setters
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// method implementations
	
	public void insert(Account account) {
		
	}
	
	public void update(Account account) {
		
	}
	
	public void update(List<Account> accounts) {
		
	}
	
	public void delete(long accountId) {
		
	}
	
	public Account find(long accountId) {
		return null;
	}
	
	public List<Account> find(List<Long> accounts) {
		return null;
	}
	
	public List<Account> find(String ownerName) {
		return null;
	}
	
	public List<Account> find(boolean locked) {
		return null;
	}
}
