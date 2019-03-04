package com.nnniu.shiro.ch12;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.format.DefaultHashFormatFactory;
import org.apache.shiro.crypto.hash.format.HexFormat;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nnniu.shiro.ch12.realm.UserRealm;
import com.nnniu.shiro.ch12.service.UserService;

/*
 * shiro相关beans
 * 注意：目前并未扫描该文件
 */
@Configuration
public class Ch12Configuration {

	// 缓存管理器，使用EhCache实现
	@Bean
	public CacheManager cacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return ehCacheManager;
	}
	
	// 加密密码
	@Bean
	public PasswordService passwordService() {
		DefaultPasswordService passwordService = new DefaultPasswordService();
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("MD5");
		hashService.setPrivateSalt(ByteSource.Util.bytes("nnniu"));
		hashService.setGeneratePublicSalt(true);
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
		hashService.setHashIterations(1);
		passwordService.setHashService(hashService);
		passwordService.setHashFormat(new HexFormat());
		passwordService.setHashFormatFactory(new DefaultHashFormatFactory());
		return passwordService;
	}
	
	// 验证密码
	@Bean
	public CredentialsMatcher credentialsMatcher() {
		PasswordMatcher passwordMatcher = new PasswordMatcher();
		passwordMatcher.setPasswordService(passwordService());
		return passwordMatcher;
	}
	
	// Realm实现
	@Bean
	public UserRealm userRealm(UserService userService) {
		UserRealm userRealm = new UserRealm();
		userRealm.setCredentialsMatcher(credentialsMatcher());
		userRealm.setUserService(userService);
		userRealm.setCachingEnabled(true);
		userRealm.setAuthenticationCachingEnabled(true);
		userRealm.setAuthenticationCacheName("authenticationCache");
		userRealm.setAuthorizationCachingEnabled(true);
		userRealm.setAuthorizationCacheName("authorizationCache");
		
		return userRealm;
	}
	
	// 安全管理器
	@Bean
	public SecurityManager securityManager(UserRealm userRealm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setCacheManager(cacheManager());
		defaultWebSecurityManager.setRealm(userRealm);
		
		return defaultWebSecurityManager;
	}
	
	// 相当于调用SecurityUtils.setSecurityManager(securityManager)
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager) {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		methodInvokingFactoryBean.setArguments(securityManager);
		return methodInvokingFactoryBean;
	}
	
	// Shiro的Web过滤器
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
		shiroFilterFactoryBean.setFilterChainDefinitions("/login=anon\n" + 
				"/unauthorized=anon\n" + 
				"/static/**=anon\n" + 
				"/authenticated=authc\n" + 
				"/role=authc,roles[admin]\n" + 
				"/permission=authc,perms[\"user:create\"]");
		return shiroFilterFactoryBean;
	}
}
