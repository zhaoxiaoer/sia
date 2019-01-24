package com.nnniu.shiro.ch3;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.MyRealm1;

public class Main2 {
	
	private static Logger logger = LoggerFactory.getLogger(Main2.class);
	
	public static void main(String[] args) {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		
		// 设置认证器authenticator及认证器authenticator的认证策略
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);
		
		// 设置授权器authorizer及权限解析器permissionResolver
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		securityManager.setAuthorizer(authorizer);
		
		// 设置Realm
		MyRealm1 myRealm1 = new MyRealm1();
		securityManager.setRealm(myRealm1);
		
		// 将securityManager绑定到SecurityUtils，方便全局使用
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhao", "123456");
		
		try {
			subject.login(token);
			
			PrincipalCollection principalCollection = subject.getPrincipals();
			Iterator iterator = principalCollection.iterator();
			while (iterator.hasNext()) {
				String principal = (String) iterator.next();
				logger.debug("principal: " + principal);
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
