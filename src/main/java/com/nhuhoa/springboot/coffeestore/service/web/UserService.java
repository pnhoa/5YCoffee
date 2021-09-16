package com.nhuhoa.springboot.coffeestore.service.web;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nhuhoa.springboot.coffeestore.dto.UserDTO;

public interface UserService extends GeneralService<UserDTO>, UserDetailsService {
	
	public UserDTO findByUserName(String userName);

	public UserDTO findByEmail(String email);

	public Long count();

	public void saveRoles(UserDTO theUserDto);

}
