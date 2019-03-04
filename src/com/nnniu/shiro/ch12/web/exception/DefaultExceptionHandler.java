package com.nnniu.shiro.ch12.web.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {

	@ExceptionHandler({UnauthenticatedException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthenticatedException(NativeWebRequest request, 
			UnauthenticatedException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.setViewName("unauthorized");
		return mv;
	}
	
}
