package com.nnniu.shiro.ch12.entity;

import java.util.HashSet;
import java.util.Set;

public class Role {
	
	private Long id;
	private String role;
	private String description;
	private boolean available = Boolean.FALSE;
	private Set<Permission> permissions = new HashSet<Permission>();
	
	public Role() {
		
	}
	
	public Role(String role, String description, boolean available) {
		this.role = role;
		this.description = description;
		this.available = available;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public Set<Permission> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Role role = (Role) o;
		if (id != null ? !id.equals(role.id) : role.id != null)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
	
	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", role='" + role + "'" +
				", description='" + description + "'" +
				", available=" + available +
				", permissions=" + permissions.toString() +
				"}";
	}
	
}
