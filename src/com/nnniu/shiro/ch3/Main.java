package com.nnniu.shiro.ch3;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		Factory<org.apache.shiro.mgt.SecurityManager> securityManagerFactory = 
				new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
		
		org.apache.shiro.mgt.SecurityManager securityManager = securityManagerFactory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhao", "123456");
		
		try {
			subject.login(token);
			
			PrincipalCollection principalCollection = subject.getPrincipals();
			Iterator iterator = principalCollection.iterator();
			while (iterator.hasNext()) {
				String principal = (String) iterator.next();
				logger.debug("pricipal: " + principal);
			}
			
			if (subject.hasRole("管理员")) {
				logger.debug("管理员");
			}
			if (subject.hasAllRoles(Arrays.asList("管理员", "开发者"))) {
				logger.debug("管理员，开发者");
			}
			if (subject.hasRole("运维人员")) {
				logger.debug("运维人员");
			}
			
//			subject.checkRole("项目经理");
			
			if (subject.isPermitted("users:delete")) {
				logger.debug("permitted: users:delete");
			}
			
			subject.logout();
		} catch (AuthenticationException e) {
			logger.error(e.toString());
		} catch (UnauthorizedException e) {
			logger.error("111: " + e.toString());
		}
	}

}
