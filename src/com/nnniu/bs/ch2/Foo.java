package com.nnniu.bs.ch2;

public class Foo {
	private String name;
	
	public Foo() {
		
	}
	
	public Foo(String name) {
		this.name = name;
	}
	
	public void init() {
		System.out.println("Foo init method is called");
	}
	
	public void destroy() {
		System.out.println("Foo destroy method is called");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
