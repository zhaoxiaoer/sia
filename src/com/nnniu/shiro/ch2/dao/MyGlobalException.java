package com.nnniu.shiro.ch2.dao;

public class MyGlobalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7619059414771489817L;
	
	public MyGlobalException(String message) {
		super(message);
	}
	
	public MyGlobalException(String message, Throwable cause) {
		super(message, cause);
	}

}
