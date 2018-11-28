package com.nnniu.bs.ch2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main2 {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("/com/nnniu/bs/ch2/ch2-beans.xml");
		
		AccountService accountService = applicationContext.getBean("accountService", 
				AccountService.class);
		System.out.println("Before money transfer");
		System.out.println("Account 1 balance: " +
				accountService.getAccount(1).getBalance());
		System.out.println("Account 2 balance: " +
				accountService.getAccount(2).getBalance());
		
		accountService.transferMoney(1, 2, 4.0);
		
		System.out.println("After money transfer");
		System.out.println("Account 1 balance: " +
				accountService.getAccount(1).getBalance());
		System.out.println("Account 2 balance: " +
				accountService.getAccount(2).getBalance());
		
		Account account1 = applicationContext.getBean("account1", Account.class);
		System.out.println("account1: " + account1.getOwnerName() + 
				", balance: " + account1.getBalance());
	}
	
}
