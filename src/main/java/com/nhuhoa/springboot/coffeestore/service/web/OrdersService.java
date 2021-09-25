package com.nhuhoa.springboot.coffeestore.service.web;

import com.nhuhoa.springboot.coffeestore.dto.OrdersDTO;
import com.nhuhoa.springboot.coffeestore.model.Cart;

public interface OrdersService extends GeneralService<OrdersDTO> {
	
	public Cart save(Cart theCart);
	
	public Cart findCartById(Long id);

	public Iterable<OrdersDTO> findByCustomer(Long id);

	public Long count();

}
