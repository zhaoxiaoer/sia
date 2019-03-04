package com.nnniu.shiro.ch12.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/", produces = "text/plain; charset=UTF-8")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView loginGet() {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			modelAndView.setViewName("login");
		} else {
			modelAndView.addObject("subject", subject);
			modelAndView.setViewName("loginSuccess");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ModelAndView loginPost(HttpServletRequest request, HttpServletResponse response) {
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名或密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名或密码错误";
		} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
			error = "登录失败多次，账户锁定60分钟";
		} else if (exceptionClassName != null) {
			error = "用户名或密码错误";
			logger.warn("其它错误: " + exceptionClassName);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		if (error != null) {
			modelAndView.addObject("error", error);
			modelAndView.setViewName("login");
		} else {
			Subject subject = SecurityUtils.getSubject();
			logger.debug("subject: " + subject.toString());
			
			modelAndView.addObject("subject", subject);
			modelAndView.setViewName("loginSuccess");
		}
		
		return modelAndView;
	}
	
}
