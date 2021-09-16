package com.nhuhoa.springboot.coffeestore.controller;

import java.util.Map;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.paging.Paging;
import com.nhuhoa.springboot.coffeestore.service.web.CustomerService;
import com.nhuhoa.springboot.coffeestore.utils.MessageUtil;

@Controller
@RequestMapping(value = "admin/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@GetMapping("/list")
	public String showListCustomer(Model theModel, @RequestParam(value="page", defaultValue = "1") int page,
								@RequestParam(value="limit", defaultValue = "5") int limit,
								@RequestParam(value="message", required = false) String message) {
		
		CustomerDTO model = new CustomerDTO();
		
		IPaging paging = new Paging(page, limit);
		
		Iterable<CustomerDTO> theCustomerDto = customerService.findAll(paging);
		
		theModel.addAttribute("customers", theCustomerDto);
		
		model.setPage(page);
		model.setLimit(limit);
		model.setTotalItem(customerService.count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / paging.getLimit()));
		
		if(message != null) {
			Map<String, String> mess =  messageUtil.getMessage(message);
			model.setAlert(mess.get("alert"));
			model.setMessage(mess.get("message"));
		}
		
		theModel.addAttribute("model", model);
		
		return "admin/customer/list-customer";
	}
	
	@GetMapping("/{id}")
	public String showCustomerDetail(Model theModel, @PathVariable("id") Long id) {
		
		CustomerDTO theCustomerDto = customerService.findById(id);
		
		theModel.addAttribute("customer", theCustomerDto);
		
		return "admin/customer/customer-detail";
	}
	
	@PostMapping("/")
	@ResponseBody  // response to page success or error
	public void deleteCustomers(@RequestBody Long[] ids) {
		try {
			for(Long id:ids) {
				customerService.remove(id);
			}
		} catch(Exception e) {
			e.printStackTrace();
		
		}
		
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("theSearchValue") String theSearchValue, Model theModel) {
		CustomerDTO model = new CustomerDTO();
		
		IPaging paging = new Paging(null, null);
		
		Iterable<CustomerDTO> theCustomerDto = customerService.search(paging, theSearchValue);
		
		theModel.addAttribute("customers", theCustomerDto);
		
		model.setPage(1);
		model.setLimit(100);
		model.setTotalItem(StreamSupport.stream(theCustomerDto.spliterator(), false).count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		
		
		theModel.addAttribute("model", model);
		
		return "admin/customer/list-customer";
	}

}
