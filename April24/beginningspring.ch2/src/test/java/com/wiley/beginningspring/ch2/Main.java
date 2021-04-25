package com.wiley.beginningspring.ch2;

import java.util.Collections;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

public class Main {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(); // annotation instead of XML for appContext
		
		applicationContext.register(Ch2Configuration.class); // registers (but doesnt initilize) this class as the java config (as opposed to XML)
															// to perform some changes on the container before initilizing it
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		environment.setActiveProfiles("dev"); // this is used to set the active profile to use
		
		MutablePropertySources propertySources = environment.getPropertySources(); // using the method creates in Ch2Configuration, gets the property source
																					// which is used to change placeholder value from here
		propertySources.addLast(new MapPropertySource("mapSource", Collections.singletonMap("name", (Object)"my foo"))); // change placeholder val from "name" to "my foo"
		
		applicationContext.refresh(); // initilizes the container and all the beans inside
		
		Foo foo = applicationContext.getBean(Foo.class); // gets the foo bean after its modifications
		System.out.println(foo.getName());		// prints the name
		
		
		
	//	AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
		
		// beans like @Repository and @Service can potentially add functionality
		// as well as create an instance method of the class its under, with 
		// lowerCase first (AccountService --> accountService bean) etc as well
		
//		System.out.println("Before money transfer");
//		System.out.println("Account 1 balance: " + accountService.getAccount(1).getBalance());
//		System.out.println("Account 2 balance: " + accountService.getAccount(2).getBalance());		
//		
//		accountService.transferMoney(1, 2, 5.0);
//		
//		System.out.println("After money transfer");
//		System.out.println("Account 1 balance: " + accountService.getAccount(1).getBalance());
//		System.out.println("Account 2 balance: " + accountService.getAccount(2).getBalance());
	}

}
