package com.nhuhoa.springboot.coffeestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@PropertySource("classpath:social-login.yml")
public class CoffeeStoreApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CoffeeStoreApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CoffeeStoreApplication.class, args);
	}
	
}
