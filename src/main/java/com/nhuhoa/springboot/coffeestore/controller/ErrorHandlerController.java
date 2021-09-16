package com.nhuhoa.springboot.coffeestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandlerController {
	
	
	@GetMapping("/error-access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	

}
