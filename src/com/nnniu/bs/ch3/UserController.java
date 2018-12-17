package com.nnniu.bs.ch3;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	private static final String[] countries = { "Turkey", 
			"United States", "Germany" };
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value="/form")
	public ModelAndView user(HttpServletRequest request) {
		User user = new User();
		user.setName("zhao");
		ModelAndView modelAndView = new ModelAndView("userForm", "user", user);
//		modelAndView.addObject("genders", Gender.values());
		modelAndView.addObject("countries", countries);
		
		System.out.println("URI: " + request.getRequestURI());
		
//		Enumeration<String> names = request.getAttributeNames();
//		while (names.hasMoreElements()) {
//			String key = names.nextElement();
//			Object obj = request.getAttribute(key);
//			System.out.println("request key: " + key + ", obj: " + obj.toString());
//		}
//		
//		ModelMap modelMap = modelAndView.getModelMap();
//		Iterator<String> iter = modelMap.keySet().iterator();
//		while (iter.hasNext()) {
//			String key = iter.next();
//			Object obj = modelMap.get(key);
//			System.out.println("key: " + key + ", obj: " + obj.toString());
//		}
		
		return modelAndView;
	}
	
	@ModelAttribute("genders")
	public Gender[] genders() {
		return Gender.values();
	}
	
	@RequestMapping(value="/result")
	public ModelAndView processUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.addObject("countries", countries);
		
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError objectError : errorList) {
				System.out.println(objectError.getDefaultMessage());
			}
			modelAndView.setViewName("userForm");
		} else {
			modelAndView.setViewName("userResult");
		}
		
		System.out.println("URI: " + request.getRequestURI());
		
		try {
			String filePath = request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/uploads/" + user.getFile().getOriginalFilename();
			System.out.println("filePath: " + filePath);
			user.getFile().transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping("/testPathVariable/{name}")
	public ModelAndView testPathVariable(@PathVariable("name") String n) {
		ModelAndView modelAndView = new ModelAndView("testPathVariable");
		
		return modelAndView;
	}

}
