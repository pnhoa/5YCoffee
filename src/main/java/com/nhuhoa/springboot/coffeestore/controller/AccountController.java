package com.nhuhoa.springboot.coffeestore.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.service.web.CustomerService;
import com.nhuhoa.springboot.coffeestore.utils.MessageUtil;

@Controller
public class AccountController {
	
	@Autowired
	private CustomerService  customerService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/account")
	public String showAccountPage(Model theModel, HttpSession session, @RequestParam(value="message", required = false) String message) {
		
		CustomerDTO theSessionCustomer = (CustomerDTO) session.getAttribute("customer");
		
		CustomerDTO theCustomer = customerService.findById(theSessionCustomer.getId());
		
		CustomerDTO model = new CustomerDTO();
		
		if(message != null) {
			Map<String, String> mess =  messageUtil.getMessage(message);
			model.setAlert(mess.get("alert"));
			model.setMessage(mess.get("message"));
		}
		
		theModel.addAttribute("model", model);
		theModel.addAttribute("customer", theCustomer);
		session.setAttribute("customer", theCustomer);
		
		
		return "web/account";
	}
	
	@PostMapping("/account/update")
	public String processingUpdateCustomer(@Valid @ModelAttribute("customer") CustomerDTO theCustomerDto, 
							BindingResult theBindingResult, Model theModel, HttpSession session) {
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 
			 	theModel.addAttribute("customer", theCustomerDto);
			 
			 return "web/account";
	      }
		 
		 String theOldPassword = theCustomerDto.getTheOldPassword();
		 if( theOldPassword != null && theOldPassword.length() > 0) {
			 CustomerDTO theOldCustomerDto = customerService.findById(theCustomerDto.getId());
			 
			 if(!passwordEncoder.matches(theOldPassword, theOldCustomerDto.getPassword())) {
				 	theModel.addAttribute("customer", theCustomerDto);
					theModel.addAttribute("registrationError", "The old password wrong.");

		        	return "web/account";
			 }
		 }
		 
		 CustomerDTO theNewCustomer = customerService.save(theCustomerDto);
		 
		
		 session.setAttribute("customer", theNewCustomer);
		
		return "redirect:/account?message=update_success";
	}
}
