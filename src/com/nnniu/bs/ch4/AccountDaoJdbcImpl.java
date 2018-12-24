package com.nnniu.bs.ch4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class AccountDaoJdbcImpl implements AccountDao {
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// getters & setters
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// method implementations
	
	public void insert(Account account) {
//		PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
//				"insert into account (owner_name, balance, access_time, locked) values (?, ?, ?, ?)",
//				new int[] { Types.VARCHAR, Types.DOUBLE, Types.TIMESTAMP, Types.BOOLEAN });
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		int count = jdbcTemplate.update(psCreatorFactory.newPreparedStatementCreator(new Object[] {
//				account.getOwnerName(), account.getBalance(),
//				account.getAccessTime(), account.isLocked()
//		}), keyHolder);
//		if (count != 1) {
//			throw new InsertFailedException("Cannot insert account");
//		}
//		account.setId(keyHolder.getKey().longValue());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String sql = "insert into account (owner_name, balance, access_time, locked) values (?, ?, ?, ?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, account.getOwnerName());
				ps.setDouble(2, account.getBalance());
				ps.setDate(3, new Date(account.getAccessTime().getTime()));
				ps.setBoolean(4, account.isLocked());
				return ps;
			}
		}, keyHolder);
		account.setId(keyHolder.getKey().longValue());
	}
	
	public void update(Account account) {
		int count = jdbcTemplate.update("update account set owner_name = ?, balance = ?, access_time = ?, locked = ? where id = ?",
				account.getOwnerName(), account.getBalance(),
				account.getAccessTime(), account.isLocked(),
				account.getId());
		if (count != 1) {
			throw new UpdateFailedException("Cannot update account");
		}
	}
	
	public void update(List<Account> accounts) {
		
	}
	
	public void delete(long accountId) {
		int count = jdbcTemplate.update("delete from account where id = ?", accountId);
		if (count != 1) {
			throw new DeleteFailedException("Cannot delete account");
		}
	}
	
	public Account find(long accountId) {
		// JdbcTemplate 封装了所有数据访问逻辑
		return jdbcTemplate.queryForObject(
				"select id, owner_name, balance, access_time, locked from account where id = ?", new RowMapper<Account>() {
					
					@Override
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						Account account = new Account();
						account.setId(rs.getLong("id"));
						account.setOwnerName(rs.getString("owner_name"));
						account.setBalance(rs.getDouble("balance"));
						account.setAccessTime(rs.getTimestamp("access_time"));
						account.setLocked(rs.getBoolean("locked"));
						return account;
					}
					
				}, accountId);
	}
	
	public List<Account> find(List<Long> accountIds) {
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("accountIds", accountIds);
		return namedParameterJdbcTemplate.query(
				"select * from account where id in (:accountIds)",
				sqlParameterSource, 
				new RowMapper<Account>() {
					
					@Override
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						Account account = new Account();
						account.setId(rs.getLong("id"));
						account.setOwnerName(rs.getString("owner_name"));
						account.setBalance(rs.getDouble("balance"));
						account.setAccessTime(rs.getTimestamp("access_time"));
						account.setLocked(rs.getBoolean("locked"));
						return account;
					}
					
				});
	}
	
	public List<Account> find(String ownerName) {
		return namedParameterJdbcTemplate.query(
				"select id, owner_name, balance, access_time, locked from account where owner_name = :ownerName",
				Collections.singletonMap("ownerName", ownerName), 
				new RowMapper<Account>() {
					
					@Override
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						Account account = new Account();
						account.setId(rs.getLong("id"));
						account.setOwnerName(rs.getString("owner_name"));
						account.setBalance(rs.getDouble("balance"));
						account.setAccessTime(rs.getTimestamp("access_time"));
						account.setLocked(rs.getBoolean("locked"));
						return account;
					}
					
				});
	}
	
	public List<Account> find(boolean locked) {
		PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
				"select * from account where locked = ?", new int[] {Types.BOOLEAN});
		return jdbcTemplate.query(psCreatorFactory.newPreparedStatementCreator(new Object[] {locked}), new RowMapper<Account>() {
			
			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account account = new Account();
				account.setId(rs.getLong("id"));
				account.setOwnerName(rs.getString("owner_name"));
				account.setBalance(rs.getDouble("balance"));
				account.setAccessTime(rs.getTimestamp("access_time"));
				account.setLocked(rs.getBoolean("locked"));
				return account;
			}
			
		});
	}
}
