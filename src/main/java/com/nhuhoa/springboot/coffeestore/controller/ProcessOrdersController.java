package com.nhuhoa.springboot.coffeestore.controller;

import java.util.Map;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhuhoa.springboot.coffeestore.dto.OrdersDTO;
import com.nhuhoa.springboot.coffeestore.dto.OrdersDetailDTO;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.paging.Paging;
import com.nhuhoa.springboot.coffeestore.service.web.OrdersDetailService;
import com.nhuhoa.springboot.coffeestore.service.web.OrdersService;
import com.nhuhoa.springboot.coffeestore.utils.MessageUtil;

@Controller
@RequestMapping("/admin/orders")
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
	
	@GetMapping("/{id}")
	public String showOrderDetailsList(Model theModel, @PathVariable("id") Long theId,
				@RequestParam(value="message", required = false) String message) {
		
		OrdersDTO theOrdersDto = ordersService.findById(theId);
		
		
		if(theOrdersDto == null) {
			
			return "redirect:list?message=error_system";
		}
		
		Iterable<OrdersDetailDTO> theOrderDetailsDto = ordersDetailService.findByOrderId(theId);
		
		if(message != null) {
			Map<String, String> mess =  messageUtil.getMessage(message);
			theOrdersDto.setAlert(mess.get("alert"));
			theOrdersDto.setMessage(mess.get("message"));
		}
		
		theModel.addAttribute("orderDetails", theOrderDetailsDto);
		theModel.addAttribute("model", theOrdersDto);
		
		
		return "admin/orders/list-order-detail";
	}
	
	@PostMapping("/update")
	public String updateStatus(Model theModel, HttpServletRequest request) {
		
		// get Order Id
		String orderIdString = request.getParameter("orderId");
		Long orderId = Long.parseLong(orderIdString);
		
		// get id and status of list order detail
		String [] idsString = request.getParameterValues("id");
		String [] statusString = request.getParameterValues("status");
		
		int i = 0;
		try {
			// update status
			for(String idString : idsString) {
				Long id = Long.parseLong(idString);
				int status = Integer.parseInt(statusString[i]);
				ordersDetailService.updateStatus(id, status);
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:" + orderId + "?message=error_system";
		}

		return "redirect:" + orderId + "?message=update_success";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("theSearchValue") String theSearchValue, Model theModel) {
		
		try {
			@SuppressWarnings("unused")
			Long id = Long.parseLong(theSearchValue);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:list?message=not_found";
		}

		OrdersDTO model = new OrdersDTO();
		
		IPaging paging = new Paging(null, null);
				
		Iterable<OrdersDTO> theOrdersDto =  ordersService.search(paging, theSearchValue);
		

		
		model.setPage(1);
		model.setLimit(100);
		model.setTotalItem(StreamSupport.stream(theOrdersDto.spliterator(), false).count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		
		
		theModel.addAttribute("model", model);
		
		
		theModel.addAttribute("orders", theOrdersDto);
		
		return "admin/orders/list-orders";
	}
}
