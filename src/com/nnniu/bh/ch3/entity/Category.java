package com.nnniu.bh.ch3.entity;

import java.util.HashSet;
import java.util.Set;

public class Category {
	private long id;
	private String title;
	private Set adverts = new HashSet();
	
	Category() {
		
	}
	
	public Category(String title) {
		this.title = title;
		this.adverts = new HashSet();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set getAdverts() {
		return adverts;
	}
	public void setAdverts(Set adverts) {
		this.adverts = adverts;
	}
}
