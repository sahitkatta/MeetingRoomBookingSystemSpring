package com.spring.comakeit.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
public class Login {
	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", role=" + role + "]";
	}

	@Id
	@Column(length=32)
	private String username;
	@Column(length=32)
	private String password;
	@Column(length=32)
	private String role;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
