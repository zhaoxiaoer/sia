package com.nnniu.shiro.ch12;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.format.DefaultHashFormatFactory;
import org.apache.shiro.crypto.hash.format.HexFormat;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ch12Configuration {

	@Bean
	public DefaultPasswordService passwordService() {
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
	
}
