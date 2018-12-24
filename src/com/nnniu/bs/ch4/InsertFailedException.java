package com.nnniu.bs.ch4;

import org.springframework.dao.DataAccessException;

public class InsertFailedException extends DataAccessException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9099164098808843073L;

	public InsertFailedException(String msg) {
		super(msg);
	}
	
}
