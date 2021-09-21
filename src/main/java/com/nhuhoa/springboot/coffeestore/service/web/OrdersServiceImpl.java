package com.nhuhoa.springboot.coffeestore.service.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.dao.IOrdersDao;
import com.nhuhoa.springboot.coffeestore.dto.OrdersDTO;
import com.nhuhoa.springboot.coffeestore.entity.Customer;
import com.nhuhoa.springboot.coffeestore.entity.Orders;
import com.nhuhoa.springboot.coffeestore.model.Cart;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	public ModelMapper mapper;
	
	@Autowired
	private IOrdersDao ordersDao;

	@Override
	public Iterable<OrdersDTO> findAll(IPaging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersDTO findById(Long id) {
		
		Orders theOrders = ordersDao.findById(id);
	
		if(theOrders == null) {
			return null;
		}
		OrdersDTO theOrdersDto = mapper.map(theOrders, OrdersDTO.class);
		
		return theOrdersDto;
	}
	
	@Override
	public Cart findCartById(Long id) {
		
		Orders theOrders = ordersDao.findById(id);
		
		if(theOrders == null) {
			return null;
		}
		OrdersDTO theOrdersDto = mapper.map(theOrders, OrdersDTO.class);
		
		Cart theCart = mapper.map(theOrdersDto, Cart.class);
		
		return theCart;
	}

	@Override
	public OrdersDTO save(OrdersDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<OrdersDTO> search(IPaging paging, String theSearchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart save(Cart theCart) {
		
		OrdersDTO theOrdersDto = mapper.map(theCart, OrdersDTO.class);
		
		Orders theOrders = mapper.map(theOrdersDto, Orders.class);
		
		Customer theCustomer = mapper.map(theOrdersDto.getCustomer(), Customer.class);
		
		theOrders.setCustomer(theCustomer);
		
		if(theOrders.getId() != null) {
			theOrders.setModefiedBy("");
			theOrders.setModifiedDate(new Date());
		} else {
			theOrders.setCreatedBy(theCustomer.getUserName());
			theOrders.setCreatedDate(new Date());
		}
		
		Orders theNewOrders = ordersDao.save(theOrders);
		
		theCart.setId(theNewOrders.getId());
		
		return theCart;
		
	}

	@Override
	public Iterable<OrdersDTO> findByCustomer(Long id) {
		
		Iterable<Orders> theOrders = ordersDao.findByCustomer(id);
		
		List<OrdersDTO> theOrdersDtos = new ArrayList<OrdersDTO>();
		
		for(Orders theOrder : theOrders) {
			OrdersDTO theOrdersDto = new OrdersDTO();
			
			theOrdersDto.setId(theOrder.getId());
			theOrdersDto.setCreatedBy(theOrder.getCreatedBy());
			theOrdersDto.setCreatedDate((Timestamp)theOrder.getCreatedDate());
			theOrdersDto.setModifiedBy(theOrder.getModefiedBy());
			theOrdersDto.setModifiedDate((Timestamp)theOrder.getModifiedDate());
			theOrdersDto.setAddress(theOrder.getAddress());
			theOrdersDto.setNote(theOrder.getNote());
			
			theOrdersDtos.add(theOrdersDto);
			
			
		}
		
		//TypeToken<Iterable<OrdersDTO>> typeToken = new TypeToken<Iterable<OrdersDTO>>() {
		//};

		//Iterable<OrdersDTO> theOrdersDtos = mapper.map(theOrders, typeToken.getType());
		
		
		return theOrdersDtos;
		
		
	}

	

}
