package com.nhuhoa.springboot.coffeestore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.dto.OrdersDTO;
import com.nhuhoa.springboot.coffeestore.dto.OrdersDetailDTO;
import com.nhuhoa.springboot.coffeestore.service.web.OrdersDetailService;
import com.nhuhoa.springboot.coffeestore.service.web.OrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrdersDetailService ordersDetailService;
	
	@GetMapping("/history")
	public String showOrderHistoryPage(Model theModel, HttpSession session) {
		
		CustomerDTO theCustomer = (CustomerDTO) session.getAttribute("customer");
		
		Iterable<OrdersDTO> theOrders =  ordersService.findByCustomer(theCustomer.getId());
		
		List<OrdersDetailDTO> theOrdersDetailss = new ArrayList<OrdersDetailDTO>();
		
		for(OrdersDTO theOrder : theOrders) {
			Long theId = theOrder.getId();
			
			List<OrdersDetailDTO> theOrdersDetails = ordersDetailService.findByOrderId(theId);
			
			for(OrdersDetailDTO ordersDetailDto : theOrdersDetails) {
				theOrdersDetailss.add(ordersDetailDto);
			}
			
		}
		
		theModel.addAttribute("orderItems", theOrdersDetailss);
		
		return "web/orders-history";
	}

}
