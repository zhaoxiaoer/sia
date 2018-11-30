package com.nnniu.bs.ch2;

import java.util.Collections;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

public class Main6 {

	public static void main(String[] argv) {
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(FooConfiguration.class);
		
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
//			System.out.println("name: " + name + ", type: " + applicationContext.getType(name));
			System.out.println("name: " + name);
		}
		
		// 设置环境
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		environment.setActiveProfiles("dev");
		// 设置特定环境下的变量值
		MutablePropertySources propertySources =
				environment.getPropertySources();
		propertySources.addLast(new MapPropertySource("mapSource", Collections.singletonMap("name", (Object) "my foo")));
		applicationContext.refresh();
		
		names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println("name: " + name + ", type: " + applicationContext.getType(name));
//			System.out.println("name: " + name);
		}
		
		Foo foo = applicationContext.getBean(Foo.class); // 通过类型查找Bean
		System.out.println(foo.getName());
	}
	
}
