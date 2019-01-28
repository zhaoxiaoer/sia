package com.nnniu.shiro.ch3;

import java.security.Key;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnniu.shiro.ch2.MyRealm3;

public class Main3 {
	
	private static Logger logger = LoggerFactory.getLogger(Main3.class);
	
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
		
		// 配置realm
		MyRealm3 myRealm3 = new MyRealm3();
//		DefaultPasswordService passwordService = new DefaultPasswordService();
		// 凭证匹配器，可以自动识别 SimpleAuthenticationInfo 中的 salt
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1);
		// 设置Realm的凭证匹配器
		myRealm3.setCredentialsMatcher(credentialsMatcher);
		
		// 设置Realm
		securityManager.setRealm(myRealm3);
		
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
