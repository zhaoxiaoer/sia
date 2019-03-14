package com.nnniu.shiro.ch12.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nnniu.shiro.ch12.entity.User;
import com.nnniu.shiro.ch12.service.UserService;

@Controller
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/", produces = "text/plain; charset=UTF-8")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView loginGet(String kickout) {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			modelAndView.setViewName("login");
			if (kickout != null && kickout.equals("1")) {
				logger.debug("kickout: " + kickout);
				modelAndView.addObject("error", "您被踢出登录");
			}
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
			// UserRealm抛出该异常
			error = "用户名或密码错误";
		} else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
			// UserRealm抛出该异常
			error = "账户被锁定";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			// PasswordMatcher抛出该异常
			error = "用户名或密码错误";
		} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
			// RetryLimitPasswordMatcher抛出该异常
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
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logoutSuccess");
		return modelAndView;
	}
	
	@RequestMapping(value = "/role")
	public ModelAndView role() {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		modelAndView.addObject("subject", subject);
		modelAndView.setViewName("hasRole");
		return modelAndView;
	}
	
	@RequestMapping(value = "/permission")
	public ModelAndView permission() {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		modelAndView.addObject("subject", subject);
		modelAndView.setViewName("hasPermission");
		return modelAndView;
	}
	
	@RequestMapping(value = "/authenticated")
	public ModelAndView authenticated() {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		modelAndView.addObject("subject", subject);
		modelAndView.setViewName("authenticated");
		return modelAndView;
	}
	
	@RequestMapping(value = "/unauthorized")
	public ModelAndView unauthorized() {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		modelAndView.addObject("subject", subject);
		modelAndView.setViewName("unauthorized");
		return modelAndView;
	}
	
//	@RequiresAuthentication
	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public ModelAndView registerGet() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ModelAndView registerPost(String username, String password) {
		logger.debug("username: " + username + ", password: " + password);
		ModelAndView modelAndView = new ModelAndView();
		if (username == null || username.equals("") 
				|| password == null || password.equals("")) {
			modelAndView.addObject("error", "用户名或密码不能为空");
			modelAndView.setViewName("register");
			return modelAndView;
		}
		
		userService.createUser(new User(username, password));
		modelAndView.setViewName("registerSuccess");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/profile")
	public ModelAndView profileGet() {
		ModelAndView mv = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		mv.addObject("subject", subject);
		mv.setViewName("profile");
		return mv;
	}
}
