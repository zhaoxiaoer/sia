package com.nnniu.bs.ch2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ch2BeanConfiguration {
	
	@Bean
	public AccountService accountService() {
		AccountServiceImpl bean = new AccountServiceImpl();
		bean.setAccountDao(accountDao());
		return bean;
	}
	
	@Bean
	public AccountDao accountDao() {
		AccountDaoInMemoryImpl bean = new AccountDaoInMemoryImpl();
		return bean;
	}
	
}
