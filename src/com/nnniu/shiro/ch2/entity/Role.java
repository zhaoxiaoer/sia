package com.nnniu.shiro.ch2.entity;

import java.util.HashSet;
import java.util.Set;

public class Role {
	
	private long id;
	private String roleName;
	private Set<Permission> permissions = new HashSet<Permission>();
	
	public Role() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
}
