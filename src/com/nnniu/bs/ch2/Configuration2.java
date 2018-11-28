package com.nnniu.bs.ch2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration2 {

	@Bean
	public Foo foo() {
		Foo foo = new Foo();
		foo.setName("Your foo");
		return foo;
	}
	
}
