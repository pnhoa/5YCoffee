package com.nhuhoa.springboot.coffeestore.dao;

import com.nhuhoa.springboot.coffeestore.entity.Customer;

public interface ICustomerDao extends IGeneralDao<Customer> {
	
	public Customer findByUserName(String userName);

	public Customer findByEmail(String email);
	
	

}
