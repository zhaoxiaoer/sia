package com.nnniu.bs.ch2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main3 {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("/com/nnniu/bs/ch2/ch2-beans2.xml");
		
		AccountService accountService = applicationContext.getBean("accountService", 
				AccountService.class);
		System.out.println("Before money transfer");
		System.out.println("Account 1 balance: " +
				accountService.getAccount(1).getBalance());
		System.out.println("Account 2 balance: " +
				accountService.getAccount(2).getBalance());
		
		accountService.transferMoney(1, 2, 2.0);
		
		System.out.println("After money transfer");
		System.out.println("Account 1 balance: " +
				accountService.getAccount(1).getBalance());
		System.out.println("Account 2 balance: " +
				accountService.getAccount(2).getBalance());
		
		String[] beans = applicationContext.getBeanDefinitionNames();
		for (String bean : beans) {
			System.out.println("name: " + bean + ", type: " + applicationContext.getType(bean));
		}
	}
	
}
