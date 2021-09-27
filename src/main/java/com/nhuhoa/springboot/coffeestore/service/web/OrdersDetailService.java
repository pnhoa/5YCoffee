package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.List;
import java.util.Map;

import com.nhuhoa.springboot.coffeestore.dto.OrdersDetailDTO;
import com.nhuhoa.springboot.coffeestore.model.CartItem;

public interface OrdersDetailService extends GeneralService<OrdersDetailDTO> {

	public CartItem save(CartItem theCartItem);

	public List<OrdersDetailDTO> findByOrderId(Long theId);
	
	public Map<Integer, String> getStatusMap();

	public void updateStatus(Long id, int status);
}
