package com.nnniu.bh.ch9.entity;

public class Product {
	
	private long id;
	private Supplier supplier;
	private String name;
	private String description;
	private double price;
	
	public Product() {
		
	}
	
	public Product(Supplier supplier, String name, String description, double price) {
		this.supplier = supplier;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Product{");
		sb.append("id=").append(id);
		sb.append(", supplier='").append(supplier.getName()).append("'");
		sb.append(", name='").append(name).append("'");
		sb.append(", description='").append(description).append("'");
		sb.append(", price=").append(price);
		sb.append("}");
		return sb.toString();
	}
	
}
