package com.nnniu.shiro.ch2;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm1 implements Realm {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	@Override
	public String getName() {
		return "myrealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) 
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
		
		return new SimpleAuthenticationInfo(username + "@1qq.com", password, getName());
	}
	
}
