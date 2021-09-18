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

import com.nhuhoa.springboot.coffeestore.service.web.CustomerService;

@Configuration
@EnableWebSecurity
@Order(2)
public class AppSecurityConfigCustomer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomOAuth2UserService oAuth2UserService;
	
	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	 @Override
	 public void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 	.antMatchers("/oauth2/**").permitAll()
		 	.antMatchers("/", "/home", "/services", "/menu", "/product", "/blog","/about").permitAll()
			.antMatchers("/checkout/**", "/cart").hasRole("CUSTOMER")
	
			.and()
			.formLogin()
				.loginPage("/login/showLoginForm")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.oauth2Login()
				.loginPage("/login/showLoginForm")
				.userInfoEndpoint().userService(oAuth2UserService)
				.and()
				.successHandler(oAuth2LoginSuccessHandler)
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error-access-denied");
	
	 }
	 
	//beans
		//bcrypt bean definition
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		//authenticationProvider bean definition
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
			auth.setUserDetailsService(customerService); //set the custom user details service
			auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
			return auth;
		}

}
