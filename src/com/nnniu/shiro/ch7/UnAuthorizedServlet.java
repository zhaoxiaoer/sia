package com.nnniu.shiro.ch7;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

//@WebServlet(name="unAuthorizedServlet", urlPatterns="/unauthorized")
public class UnAuthorizedServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		request.setAttribute("subject", subject);
		request.getRequestDispatcher("/WEB-INF/jsp/unauthorized.jsp")
			.forward(request, response);
	}
	
}
