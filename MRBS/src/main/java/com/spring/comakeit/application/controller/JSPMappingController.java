package com.spring.comakeit.application.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class JSPMappingController {
	@RequestMapping("/")
	ModelAndView index() {
		return new ModelAndView("index.jsp");
	}
}
