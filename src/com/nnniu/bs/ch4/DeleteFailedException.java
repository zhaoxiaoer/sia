package com.nnniu.bs.ch4;

import org.springframework.dao.DataAccessException;

public class DeleteFailedException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7397382726799251854L;

	public DeleteFailedException(String msg) {
		super(msg);
	}
	
}
