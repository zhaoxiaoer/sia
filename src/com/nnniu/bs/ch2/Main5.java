package com.nnniu.bs.ch2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main5 {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("/com/nnniu/bs/ch2/foo-beans.xml");
		Foo foo1 = applicationContext.getBean("foo1", Foo.class);
		System.out.println(foo1.getName());
		Foo foo2 = applicationContext.getBean("foo2", Foo.class);
		System.out.println(foo2.getName());
		
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println("name: " + name + ", type: " + applicationContext.getType(name));
		}
	}

}
