package com.nhuhoa.springboot.coffeestore.dao;

import java.util.List;

import com.nhuhoa.springboot.coffeestore.entity.OrdersDetail;

public interface IOrdersDetailDao extends IGeneralDao<OrdersDetail> {

	List<OrdersDetail> findByOrderId(Long theId);

}
