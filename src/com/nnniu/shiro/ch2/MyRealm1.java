package com.nnniu.shiro.ch2;

import java.util.Iterator;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm1 extends AuthorizingRealm {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * 认证
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		logger.debug("1111111111111111111");
		
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		if (!"zhao".equals(username)) {
			throw new UnknownAccountException();
		}
		if (!"123456".equals(password)) {
			throw new IncorrectCredentialsException();
		}
		
		return new SimpleAuthenticationInfo(username + "@qq.com", password, getName());
	}
	
	/**
	 * 授权
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.debug("22222222222222222222");
		
		Iterator iterator = principals.iterator();
		if (iterator.hasNext()) {
			String principal = (String) iterator.next();
			logger.debug("principal--" + principal);
			if (principal.equals("zhao@qq.com")) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addRole("运维人员");
				authorizationInfo.addStringPermission("users2:create");
				return authorizationInfo;
			}
		}
		
		return null;
	}
	
}
