package com.spring.comakeit.application.repository;

import com.spring.comakeit.application.entity.Login;

public interface ILoginRepository {
	public Login authenticate(String username, String password);
}
