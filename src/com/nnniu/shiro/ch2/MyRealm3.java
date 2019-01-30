package com.nnniu.shiro.ch2;

import java.util.Iterator;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm3 extends AuthorizingRealm {
	
	private static Logger logger = LoggerFactory.getLogger(MyRealm3.class);
	
	private PasswordService passwordService;
	
	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	/**
	 * 认证
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		logger.debug("1111111111111111112");
		
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		
		// 新用户或重置密码
//		HashRequest request = new HashRequest.Builder()
//				.setSource(ByteSource.Util.bytes(password))
//				.setIterations(1).build();
//		Hash hash = ((DefaultPasswordService) passwordService).getHashService().computeHash(request);
		Hash hash = ((DefaultPasswordService) passwordService).hashPassword(password);
		String pubSalt = hash.getSalt().toHex();
		String secPwd = hash.toHex();
		logger.debug("secPwd: " + secPwd + ", pubSalt: " + pubSalt);
		
//		// 根据公私钥手动MD5原始密码
//		byte[] priBytes = "mm".getBytes();
//		byte[] pubBytes = hash.getSalt().getBytes();
//		byte[] total = new byte[priBytes.length + pubBytes.length];
//		System.arraycopy(priBytes, 0, total, 0, priBytes.length);
//		System.arraycopy(pubBytes, 0, total, 0 + priBytes.length, pubBytes.length);
//		String md5Pwd = new Md5Hash(password, ByteSource.Util.bytes(total)).toHex();
//		logger.debug("md5Pwd: " + md5Pwd);
		
		SimpleHash hash2 = new SimpleHash("MD5");
		hash2.setBytes(Hex.decode(secPwd));
		hash2.setSalt(ByteSource.Util.bytes(Hex.decode(pubSalt)));
		return new SimpleAuthenticationInfo(username, hash2, getName());
		
//		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
//		String pubSalt = randomNumberGenerator.nextBytes().toHex();
//		logger.debug("publicSalt: " + pubSalt);
//		
//		String md5Pwd = new Md5Hash(password, "" + pubSalt).toHex();
//		logger.debug("md5Pwd: " + md5Pwd + ", " + passwordService.encryptPassword("password"));
//		
//		return new SimpleAuthenticationInfo(username + "@qq.com", md5Pwd, ByteSource.Util.bytes("" + pubSalt), getName());
		
//		logger.debug("1111111111111111113: " + username + ", " + password);
////		return new SimpleAuthenticationInfo(username + "@qq.com", passwordService.encryptPassword(password), getName());
//		String algorithmName = "MD5";
//		String priSalt = "mm";
//		String pubSalt = new SecureRandomNumberGenerator().nextBytes().toHex();
//		String secPwd = new SimpleHash(algorithmName, password, priSalt + pubSalt, 1).toHex();
//		logger.debug("secPwd: " + secPwd + ", public salt: " + pubSalt);
//		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
//				username + "@qq.com", secPwd, getName());
//		simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(priSalt + pubSalt));
//		
//		return simpleAuthenticationInfo;
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
