package com.spring.comakeit.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.comakeit.application.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String>, ILoginRepository {
	@Query("SELECT l from Login l where l.username= :username and l.password= :password")
	public Login authenticate(@Param("username") String username, @Param("password") String password);
}
