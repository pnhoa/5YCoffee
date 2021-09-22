package com.nhuhoa.springboot.coffeestore.controller;

import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.nhuhoa.springboot.coffeestore.dto.ContactDTO;
import com.nhuhoa.springboot.coffeestore.service.web.ContactService;
import com.nhuhoa.springboot.coffeestore.utils.MessageUtil;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/contact")
	public String showContactPage(Model theModel, @RequestParam(value = "messageNo", required = false) String message) {
		
		ContactDTO theContactDto = new ContactDTO();
		
		if(message != null) {
			Map<String, String> mess =  messageUtil.getMessage(message);
			theContactDto.setAlert(mess.get("alert"));
			theContactDto.setMessageNo(mess.get("message"));
		}
		
		theModel.addAttribute("contact", theContactDto);
		
		return "web/contact";
	}
	
	@PostMapping("/contact/send")
	public String processingContact(@Valid @ModelAttribute("contact") ContactDTO theContactDTO, BindingResult theBindingResult, Model theModel) {
		// form validation
		 if (theBindingResult.hasErrors()){
			 
			 theModel.addAttribute("contact", theContactDTO);
			 
			 return "web/contact";
	     }
		 
		 contactService.save(theContactDTO);
		 
		return "redirect:/contact?messageNo=sending_success";
	}

}
