package com.nnniu.shiro.ch7;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebServlet(name="authenticatedServlet", urlPatterns="/authenticated")
public class AuthenticatedServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(RoleServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("doGet");
		
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/jsp/authenticated.jsp")
				.forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
				.forward(request, response);
		}
	}
	
}
