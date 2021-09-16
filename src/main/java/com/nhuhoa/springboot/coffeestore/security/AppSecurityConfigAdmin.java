package com.nhuhoa.springboot.coffeestore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nhuhoa.springboot.coffeestore.service.web.UserService;

@Configuration
@EnableWebSecurity
@Order(1)
public class AppSecurityConfigAdmin extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;

	
	@Autowired
	private CustomAdminAuthenticationSuccessHandler customAdminAuthenticationSuccessHandler;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderForAdmin());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.antMatcher("/admin/**")
         		.authorizeRequests()
         		.antMatchers("/admin","/admin/", "/admin/customers/list", "/admin/users/list", "/admin/products/list").hasRole("EMPLOYEE")
         		.antMatchers("/admin/products/edit**", "/admin/**/**").hasRole("MANAGER")
         		.antMatchers("/admin/users/edit**","/admin/users/roles**").hasRole("ADMIN")
         		.antMatchers("admin/login/**").permitAll()
         
         .and()
         	.formLogin()
         	.loginPage("/admin/login/showLoginForm")
         	.loginProcessingUrl("/admin/authenticateTheUser")
			.successHandler(customAdminAuthenticationSuccessHandler)
			.permitAll()
         
			.and()
			.logout().logoutUrl("/admin/j_spring_security_logout").permitAll()
			.deleteCookies("JSESSIONID")
         
         .and()
		 .exceptionHandling().accessDeniedPage("/error-access-denied");
   }
	
	@Bean("passwordEncoderForAdmin")
	public BCryptPasswordEncoder passwordEncoderForAdmin() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean("authenticationProviderForAdmin")
	public DaoAuthenticationProvider authenticationProviderForAdmin() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoderForAdmin()); //set the password encoder - bcrypt
		return auth;
	}


}
