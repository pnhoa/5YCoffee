package com.nhuhoa.springboot.coffeestore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhuhoa.springboot.coffeestore.dto.OrdersDTO;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.paging.Paging;
import com.nhuhoa.springboot.coffeestore.service.web.OrdersDetailService;
import com.nhuhoa.springboot.coffeestore.service.web.OrdersService;
import com.nhuhoa.springboot.coffeestore.utils.MessageUtil;

@Controller
@RequestMapping("/admin/orders/")
public class ProcessOrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrdersDetailService ordersDetailService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@GetMapping("/list")
	public String showOrdersList(Model theModel, @RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit,
			@RequestParam(value="message", required = false) String message) {
		
		OrdersDTO model = new OrdersDTO();
		
		IPaging paging = new Paging(page, limit);
				
		Iterable<OrdersDTO> theOrdersDto =  ordersService.findAll(paging);
		
		model.setPage(page);
		model.setLimit(limit);
		model.setTotalItem(ordersService.count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / paging.getLimit()));
		
		if(message != null) {
			Map<String, String> mess =  messageUtil.getMessage(message);
			model.setAlert(mess.get("alert"));
			model.setMessage(mess.get("message"));
		}
		
		theModel.addAttribute("model", model);
		theModel.addAttribute("orders", theOrdersDto);
		
		return "admin/orders/list-orders";
	}
}
