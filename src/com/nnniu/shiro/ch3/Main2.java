package com.nnniu.shiro.ch3;

import java.security.Key;
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
		
		// Base64编码解码
		String str = "hello";
		String base64Encoded = Base64.encodeToString(str.getBytes());
		String str2 = Base64.decodeToString(base64Encoded);
		logger.debug("str2: " + str2);
		
		// 16进制编码解码
		str = "hello2";
		String hexEncoded = Hex.encodeToString(str.getBytes());
		str2 = new String(Hex.decode(hexEncoded.getBytes()));
		logger.debug("str2: " + str2);
		
		// 散列转换
		str = "hello";
		String salt = "123";
		String md5 = new Md5Hash(str, salt).toString();
		String md52 = new Md5Hash(str + salt).toString();
		String md53 = new Md5Hash(salt + str).toString();
		logger.debug("md5: " + md5 + ", md52: " + md52 + ", md53: " + md53);
		
		// 默认算法SHA-512
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("SHA-512");
		// 私盐，默认无
		hashService.setPrivateSalt(new SimpleByteSource("123"));
		// 是否生成公盐，默认false
		hashService.setGeneratePublicSalt(true);
		// 用于生成公盐的随机数产生器
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
		// 生成Hash值的迭代次数
		hashService.setHashIterations(1);
		HashRequest request = new HashRequest.Builder()
				.setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
				.setSalt(ByteSource.Util.bytes("123")).setIterations(1).build();
		String md5Str = hashService.computeHash(request).toString();
		String md5Str2 = new Md5Hash("123" + "123" + "hello").toString();
		logger.debug("md5Str: " + md5Str + ", md5Str2: " + md5Str2);
		
		// 对称式加密/解密算法
		// AES算法实现
		AesCipherService aesCipherService = new AesCipherService();
		// 设置key长度
		aesCipherService.setKeySize(128);
		// 生成key
		Key key = aesCipherService.generateNewKey();
		String text = "hello";
		// 加密
		String encText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
		// 解密
		String decText = new String(aesCipherService.decrypt(Hex.decode(encText.getBytes()), key.getEncoded()).getBytes());
		logger.debug("encText: " + encText + ", decText: " + decText);
	}

}
