package com.nhuhoa.springboot.coffeestore.dao;

import com.nhuhoa.springboot.coffeestore.entity.Category;

public interface ICategoryDao extends IGeneralDao<Category> {
	
	Category findOneByCode(String theCode);

}
