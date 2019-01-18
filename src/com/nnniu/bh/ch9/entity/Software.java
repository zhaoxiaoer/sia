package com.nnniu.bh.ch9.entity;

public class Software extends Product {
	
	private String version;
	
	Software() {
		
	}
	
	public Software(Supplier supplier, String name, String description,
			double price, String version) {
		super(supplier, name, description, price);
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(super.toString());
		sb.append(":Software{");
		sb.append("version='").append(version).append("'");
		sb.append("}");
		return sb.toString();
	}
	
}
