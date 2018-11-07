package com.nnniu.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ProcessTimeFilter implements Filter {
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		
		/* 执行时间 */
		long time = System.currentTimeMillis();
		chain.doFilter(req, response);
		time = System.currentTimeMillis() - time;
		System.out.println(req.getRequestURL() + ": " + time + "ms");
	}
	
	@Override
	public void destroy() {
		
	}
}
