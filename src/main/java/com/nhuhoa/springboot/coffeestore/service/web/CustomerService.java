package com.nhuhoa.springboot.coffeestore.service.web;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.utils.Provider;

public interface CustomerService extends GeneralService<CustomerDTO>, UserDetailsService {
	
	public CustomerDTO findByUserName(String userName);

	public CustomerDTO findByEmail(String email);

	public CustomerDTO createCustomerAfteOAuthLoginSuccess(String name, String email, Provider provider);
	
	public Long count();

}
