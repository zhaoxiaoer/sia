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

@WebServlet(name="logoutServlet", urlPatterns="/logout")
public class LogoutServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		logger.debug("principal: " + subject.getPrincipal());
		subject.logout();
		request.getRequestDispatcher("/WEB-INF/jsp/logoutSuccess.jsp")
			.forward(request, response);
	}
	
}
