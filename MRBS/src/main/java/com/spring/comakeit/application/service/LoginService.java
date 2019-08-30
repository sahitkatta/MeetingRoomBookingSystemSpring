package com.spring.comakeit.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.comakeit.application.entity.Login;
import com.spring.comakeit.application.repository.LoginRepository;


@Service
public class LoginService {
	@Autowired
	LoginRepository loginRepository;
	public Login authenticate(Login user) {
		return loginRepository.authenticate(user.getUsername(), user.getPassword());
	}
}
