package com.nhuhoa.springboot.coffeestore.service.api;

import com.nhuhoa.springboot.coffeestore.entity.Role;


public interface IRoleService extends IGeneralService<Role> {
	
	public Role findByCode(String theCode);

}
