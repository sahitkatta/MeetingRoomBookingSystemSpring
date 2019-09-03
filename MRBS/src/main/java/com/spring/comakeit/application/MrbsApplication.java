package com.spring.comakeit.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.spring.comakeit.application.constants.Constants;

@SpringBootApplication
@ComponentScan("com.spring.comakeit.application")
@EnableJpaRepositories
public class MrbsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(MrbsApplication.class, args);
		String serverPort = configurableApplicationContext.getEnvironment().getProperty("server.port");
		Constants.baseURL = "http://localhost:" + serverPort + "/";

	}

}
