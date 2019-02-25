package com.nnniu.shiro.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Analysis implements ApplicationContextAware {
	
	private static Logger logger = LoggerFactory.getLogger(Analysis.class);
	
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/beans", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public String getBeans() {
		logger.debug("/beans");
		
		StringBuilder builder = new StringBuilder();
		String[] names = applicationContext.getParent().getBeanDefinitionNames();
		builder.append("spring context:\n");
		for (String name : names) {
			builder.append("  name: " + name + ", type: " + applicationContext.getType(name) + "\n");
		}
		
		names = applicationContext.getBeanDefinitionNames();
		builder.append("springmvc context:\n");
		for (String name : names) {
			builder.append("  name: " + name + ", type: " + applicationContext.getType(name) + "\n");
		}
		
		
		return builder.toString();
	}

}
