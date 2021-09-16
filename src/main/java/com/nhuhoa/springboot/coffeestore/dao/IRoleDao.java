package com.nhuhoa.springboot.coffeestore.dao;

import com.nhuhoa.springboot.coffeestore.entity.Role;

public interface IRoleDao{
	
	public Iterable<Role> findAll();
	Role findRoleByCode(String code);
}
