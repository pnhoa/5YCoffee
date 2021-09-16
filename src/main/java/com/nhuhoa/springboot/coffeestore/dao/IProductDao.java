package com.nhuhoa.springboot.coffeestore.dao;

import com.nhuhoa.springboot.coffeestore.entity.Product;

public interface IProductDao extends IGeneralDao<Product> {

	Iterable<Product> findByCategoryId(Long theId); 
}
