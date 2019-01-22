package com.nnniu.shiro.ch2.entity;

import java.util.HashSet;
import java.util.Set;

public class User {

	private long id;
	private String username;
	private String password;
	private String passwordSalt;
	private Set<Role> roles = new HashSet<Role>();
	
	public User() {
		
	}
	
	public User(String username, String password, String passwordSalt) {
		this.username = username;
		this.password = password;
		this.passwordSalt = passwordSalt;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
