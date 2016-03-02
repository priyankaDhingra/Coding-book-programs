package com.storphins.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {
	@RequestMapping("/greeting")
	public ModelAndView greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		
		 return new ModelAndView("WEB-INF/templates/greeting.jsp", "now", now);
		//return "greeting";
	}
}
