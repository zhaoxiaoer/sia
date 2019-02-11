package com.nnniu.shiro.ch6;

import java.util.Iterator;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.entity.User;
import com.nnniu.shiro.ch2.service.UserService;
import com.nnniu.shiro.ch2.service.impl.UserServiceImpl;

public class MyRealm extends AuthorizingRealm {
	
	private static Logger logger = LoggerFactory.getLogger(MyRealm.class);
	private UserService userService = new UserServiceImpl();

	/**
	 * 认证
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		logger.debug("1111111111111111112");
		String username = (String) token.getPrincipal();
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException();
		}
		
		// 使用Realm的CredentialsMatcher进行密码匹配，如果觉得CredentialsMatcher不好，
		// 可以在此自己判断
		SimpleHash hash2 = new SimpleHash("MD5");
		hash2.setBytes(Hex.decode(user.getPassword()));
		hash2.setSalt(ByteSource.Util.bytes(Hex.decode(user.getSalt())));
		return new SimpleAuthenticationInfo(username, hash2, getName());
	}
	
	/**
	 * 授权
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.debug("22222222222222222222");
		
//		Iterator iterator = principals.iterator();
//		if (iterator.hasNext()) {
//			String principal = (String) iterator.next();
//			logger.debug("principal--" + principal);
//			if (principal.equals("zhao@qq.com")) {
//				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//				authorizationInfo.addRole("运维人员");
//				authorizationInfo.addStringPermission("users2:create");
//				return authorizationInfo;
//			}
//		}
//		
//		return null;
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(username));
		authorizationInfo.setStringPermissions(userService.findPermissions(username));
		return authorizationInfo;
	}
	
}
