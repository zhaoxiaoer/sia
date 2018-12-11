package com.nnniu.bs.ch3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	private static final String[] countries = { "Turkey", 
			"United States", "Germany" };
	
	@RequestMapping(value="/form")
	public ModelAndView user() {
		User user = new User();
		user.setName("zhao");
		ModelAndView modelAndView = new ModelAndView("userForm", "user", user);
//		modelAndView.addObject("genders", Gender.values());
		modelAndView.addObject("countries", countries);
		return modelAndView;
	}
	
	@ModelAttribute("genders")
	public Gender[] genders() {
		return Gender.values();
	}
	
	@RequestMapping(value="/result")
	public ModelAndView processUser(User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userResult");
		modelAndView.addObject("u", user);
		return modelAndView;
	}
	
	@RequestMapping("/testPathVariable/{name}")
	public ModelAndView testPathVariable(@PathVariable("name") String n) {
		ModelAndView modelAndView = new ModelAndView("testPathVariable");
		
		return modelAndView;
	}

}