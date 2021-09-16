package com.nhuhoa.springboot.coffeestore.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletInit extends SpringBootServletInitializer {

	@Bean
	public FilterRegistrationBean<MySiteMeshFilter> siteMeshFilter() {
		
		FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean = new FilterRegistrationBean<MySiteMeshFilter>();
		
		filterRegistrationBean.setFilter(new MySiteMeshFilter()); 
		filterRegistrationBean.addUrlPatterns("/*");
		
		return filterRegistrationBean;
	}

}
