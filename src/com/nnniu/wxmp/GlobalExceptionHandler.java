package com.nnniu.wxmp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handle404Error(HttpServletRequest request, HttpServletResponse response, Exception e) {
		return "No Handler Found!";
	}

	@ExceptionHandler(GetAccessTokenException.class)
	@ResponseBody
	public String handleAccessTokenError() {
		return "Get Access Token Exception!";
	}
	
}
