package com.nhuhoa.springboot.coffeestore.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.service.web.CustomerService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private CustomerService customerService;
	
	public Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showRegisterPage(Model theModel) {
		
		theModel.addAttribute("customer", new CustomerDTO());
		
		return "web/register";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("customer") CustomerDTO theCustomerDTO, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theCustomerDTO.getUserName();
		String email = theCustomerDTO.getEmail();
		logger.info(">>> Processing registration form for: " + userName);
		logger.info(">>> Email: " + email);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "web/register";
	        }

		// check the database if user already exists
        CustomerDTO existingUsername = customerService.findByUserName(userName);
        if (existingUsername != null){
        	theModel.addAttribute("customer", new CustomerDTO());
			theModel.addAttribute("registrationError", "Username already exists.");

			logger.warning("Username already exists.");
        	return "web/register";
        }
     // check the database if email already exists
        CustomerDTO existingEmail = customerService.findByEmail(email);
        if (existingEmail != null){
        	theModel.addAttribute("customer", new CustomerDTO());
			theModel.addAttribute("registrationError", "Email already exists.");

			logger.warning("Email already exists.");
        	return "web/register";
        }
        
        
        
        // create user account        						
        customerService.save(theCustomerDTO);
        
        logger.info("Successfully created customer: " + userName);
        
        return "redirect:/login/showLoginForm";		
	}

}
