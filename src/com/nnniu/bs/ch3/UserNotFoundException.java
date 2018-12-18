package com.nnniu.bs.ch3;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String name) {
		super("User not found with name: " + name);
	}
	
}
