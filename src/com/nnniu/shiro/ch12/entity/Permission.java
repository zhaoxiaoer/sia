package com.nnniu.shiro.ch12.entity;

public class Permission {
	
	private Long id;
	private String permission;
	private String description;
	private boolean available = Boolean.FALSE;
	
	public Permission() {
		
	}
	
	public Permission(String permission, String description, boolean available) {
		this.permission = permission;
		this.description = description;
		this.available = available;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPermission() {
		return permission;
	}
	
	public void setPermission(String permission) {
		this.permission = permission;
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Permission role = (Permission) o;
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
		return "Permission{" +
				"id=" + id +
				", permission='" + permission + "'" +
				", description='" + description + "'" +
				", available=" + available +
				"}";
	}
}
