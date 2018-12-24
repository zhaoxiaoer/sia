package com.nnniu.bs.ch4;

import org.springframework.dao.DataAccessException;

public class UpdateFailedException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3991399522421293280L;

	public UpdateFailedException(String msg) {
		super(msg);
	}
	
}
