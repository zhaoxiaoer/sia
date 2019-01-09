package com.nnniu.bh.ch3;

public class AdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -872557719752215546L;

	public AdException(String message) {
		super(message);
	}
	
	public AdException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
