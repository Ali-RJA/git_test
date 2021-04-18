package com.wiley.beginningspring.ch2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/wiley/beginningspring/ch2/ch2-beans.xml");
		
		AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
		
		// beans like @Repository and @Service can potentially add functionality
		// as well as create an instance method of the class its under, with 
		// lowerCase first (AccountService --> accountService bean) etc as well
		
		System.out.println("Before money transfer");
		System.out.println("Account 1 balance: " + accountService.getAccount(1).getBalance());
		System.out.println("Account 2 balance: " + accountService.getAccount(2).getBalance());		
		
		accountService.transferMoney(1, 2, 5.0);
		
		System.out.println("After money transfer");
		System.out.println("Account 1 balance: " + accountService.getAccount(1).getBalance());
		System.out.println("Account 2 balance: " + accountService.getAccount(2).getBalance());
	}

}
