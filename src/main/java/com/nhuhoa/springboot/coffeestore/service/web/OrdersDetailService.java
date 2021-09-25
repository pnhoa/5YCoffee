package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.List;

import com.nhuhoa.springboot.coffeestore.dto.OrdersDetailDTO;
import com.nhuhoa.springboot.coffeestore.model.CartItem;

public interface OrdersDetailService extends GeneralService<OrdersDetailDTO> {

	public CartItem save(CartItem theCartItem);

	public List<OrdersDetailDTO> findByOrderId(Long theId);
}
