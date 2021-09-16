package com.nhuhoa.springboot.coffeestore.dao;

import com.nhuhoa.springboot.coffeestore.entity.User;

public interface IUserDao extends IGeneralDao<User> {
	
	 public User findByUserName(String userName);
	    
	 public User findByEmail(String email);

}
