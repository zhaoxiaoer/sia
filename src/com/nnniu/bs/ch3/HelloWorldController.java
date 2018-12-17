package com.nnniu.bs.ch3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}

	@RequestMapping(value="/helloworld")
	public ModelAndView sayHello(HttpServletRequest request) {
		// 有多种方式可以获取WebApplicationContext，但下面这两种方式返回值都是null，目前只有通
		// 过 ApplicationContextAware 接口才能正常获取到
		// 1
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		// 2
//		ServletContext servletContext = request.getSession().getServletContext();
//		WebApplicationContext webApplicationContext = (WebApplicationContext) servletContext.getAttribute(
//				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//		System.out.println(webApplicationContext);
//		String[] names = webApplicationContext.getBeanDefinitionNames();
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
//			System.out.println("name: " + name + ", type: " + webApplicationContext.getType(name));
			System.out.println("name: " + name + ", type: ");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hello World");
		mv.setViewName("helloworld");
		return mv;
	}
	
	@RequestMapping(value="/beans")
	public ModelAndView getBeans() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("beans");
		
		String[] names = applicationContext.getBeanDefinitionNames();
		StringBuilder builder = new StringBuilder();
		for (String name : names) {
//			System.out.println("name: " + name + ", type: " + webApplicationContext.getType(name));
//			System.out.println("name: " + name + ", type: ");
			builder.append("name: " + name + ", type: " + "<br />");
		}
		modelAndView.addObject("beans", builder.toString());
		
		return modelAndView;
	}
	
}
