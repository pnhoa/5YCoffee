package com.nhuhoa.springboot.coffeestore.service.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.dao.IOrdersDetailDao;
import com.nhuhoa.springboot.coffeestore.dto.OrdersDetailDTO;
import com.nhuhoa.springboot.coffeestore.entity.Orders;
import com.nhuhoa.springboot.coffeestore.entity.OrdersDetail;
import com.nhuhoa.springboot.coffeestore.entity.Product;
import com.nhuhoa.springboot.coffeestore.model.CartItem;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Service
@Transactional
public class OrdersDetailServiceImpl implements OrdersDetailService {
	
	@Autowired
	public ModelMapper mapper;
	
	@Autowired
	private IOrdersDetailDao ordersDetailDao;

	@Override
	public Iterable<OrdersDetailDTO> findAll(IPaging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersDetailDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersDetailDTO save(OrdersDetailDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<OrdersDetailDTO> search(IPaging paging, String theSearchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartItem save(CartItem theCartItem) {
		
		OrdersDetailDTO theOrdersDetailDto = mapper.map(theCartItem, OrdersDetailDTO.class);
		
		
		OrdersDetail theOrdersDetail = mapper.map(theOrdersDetailDto, OrdersDetail.class);
		
		Product theProduct = mapper.map(theCartItem.getProduct(), Product.class);
		
		Orders theOrders = mapper.map(theCartItem.getCart(), Orders.class);
		
		theOrdersDetail.setProduct(theProduct);
		theOrdersDetail.setOrders(theOrders);
		
		if(theOrdersDetail.getId() != null) {
			theOrdersDetail.setModefiedBy("");
			theOrdersDetail.setModifiedDate(new Date());
		} else {
			theOrdersDetail.setCreatedBy(theOrders.getCustomer().getUserName());
			theOrdersDetail.setCreatedDate(new Date());
			theOrdersDetail.setStatus(1);
		}
		
		ordersDetailDao.save(theOrdersDetail);
		
		return theCartItem;
	}

	@Override
	public List<OrdersDetailDTO> findByOrderId(Long theId) {
		
		List<OrdersDetail> theOrdersDetails = ordersDetailDao.findByOrderId(theId);
		
		List<OrdersDetailDTO> theOrdersDetailDtos = new ArrayList<OrdersDetailDTO>();
		
		for(OrdersDetail theOrdersDetail : theOrdersDetails) {
			
			OrdersDetailDTO theOrdersDetailDto = new OrdersDetailDTO();
			theOrdersDetailDto.setId(theOrdersDetail.getId());
			theOrdersDetailDto.setCreatedDate((Timestamp) theOrdersDetail.getCreatedDate());
			theOrdersDetailDto.setQuantity(theOrdersDetail.getQuantity());
			theOrdersDetailDto.setTotalPrice(theOrdersDetail.getTotalPrice());
			if(theOrdersDetail.getStatus() == 1) {
				theOrdersDetailDto.setStatusMsg("Processing");
			} else {
				theOrdersDetailDto.setStatusMsg("Delivery successful");
			}
			theOrdersDetailDto.setProduct(theOrdersDetail.getProduct());
			
			theOrdersDetailDtos.add(theOrdersDetailDto);
		}
		
		//TypeToken<Iterable<OrdersDetailDTO>> typeToken = new TypeToken<Iterable<OrdersDetailDTO>>() {
		//};
		
		//List<OrdersDetailDTO> theOrdersDetailDto = mapper.map(theOrdersDetails, typeToken.getType());
		
		return theOrdersDetailDtos;
	}

}
