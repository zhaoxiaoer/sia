package com.nnniu.bs.ch2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main4 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(Configuration1.class, Configuration2.class);
		Foo foo = applicationContext.getBean("foo", Foo.class);
		System.out.println(foo.getName());
	}

}
