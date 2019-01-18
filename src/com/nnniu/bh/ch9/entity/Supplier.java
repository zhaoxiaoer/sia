package com.nnniu.bh.ch9.entity;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
	
	private long id;
	private String name;
	private List<Product> products = new ArrayList<Product>();
	
	public Supplier() {
		
	}
	
	public Supplier(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Supplier{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append("'");
		sb.append(", products=").append(products);
		sb.append("}");
		return sb.toString();
	}
	
}
