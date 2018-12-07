package com.nnniu.bs.ch3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	private static final String[] countries = { "Turkey", 
			"United States", "Germany" };
	
	@RequestMapping(value="/form")
	public ModelAndView user() {
		ModelAndView modelAndView = new ModelAndView("userForm", "user", new User());
//		modelAndView.addObject("genders", "aaa");
//		modelAndView.addObject("countries", countries);
		return modelAndView;
	}

}
