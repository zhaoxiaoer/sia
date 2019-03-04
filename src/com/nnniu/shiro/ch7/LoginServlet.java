package com.nnniu.shiro.ch7;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebServlet(name="loginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("doGet");
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
				.forward(request, response);
		} else {
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp")
				.forward(request, response);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("doPost");
		
		String error = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "用户名或密码错误";
		} catch (IncorrectCredentialsException e) {
			error = "用户名或密码错误";
		} catch (AuthenticationException e) {
			// 其它错误，比如锁定，如果想单独处理，请单独catch
			error = "其它错误：" + e.getMessage();
		}
		
		if (error != null) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
				.forward(request, response);
		} else {
			Session session = SecurityUtils.getSubject().getSession();
			logger.debug("id: " + session.getId());
			logger.debug("host: " + session.getHost());
			logger.debug("timeout: " + session.getTimeout());
//			session.setTimeout(60000L);
			
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp")
				.forward(request, response);
		}
	}
	
}
