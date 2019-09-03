package com.spring.comakeit.application.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.comakeit.application.entity.Login;
import com.spring.comakeit.application.service.LoginService;



@RestController
@RequestMapping("/login")
public class LoginRestController {
	@Autowired
	private LoginService loginService;
	//POST
	/*
	 * authenticate
	 * */
	@RequestMapping("/authN")
	@PostMapping
	Login authenticate(@RequestBody Login user) {
		return loginService.authenticate(user);
	}
}
