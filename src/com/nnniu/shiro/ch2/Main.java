package com.nnniu.shiro.ch2;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	// log4j原始API
//	private static Logger logger = LogManager.getLogger(Main.class);
	// slf4j API
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		// 1. 获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
//		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
		
		// 2. 得到SecurityManager实例，并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		// 3. 得到Subject，创建用户名/密码身份验证Token （用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhao", "123456");
		
		try {
			logger.debug("aaaaaaaa:");
			// 4. 登录，即身份验证
			subject.login(token);
			
			// 得到一个身份集合，其包含了Realm验证成功的身份信息
			PrincipalCollection principalCollection = subject.getPrincipals();
			Iterator iterator = principalCollection.iterator();
			while (iterator.hasNext()) {
				Object principal = iterator.next();
				logger.debug("principal: " + principal.toString());
			}
		} catch (AuthenticationException e) {
			// 5. 身份验证失败
			logger.debug("bbbbbbbbb: " + e.toString());
			return;
		}
		
		// 6. 用户已经登录
		logger.debug("用户已登录");
		
		// 7. 退出
		subject.logout();
	}
	
}
