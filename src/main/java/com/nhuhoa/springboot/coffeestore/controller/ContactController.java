package com.nhuhoa.springboot.coffeestore.controller;

import java.util.Map;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhuhoa.springboot.coffeestore.dto.ContactDTO;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.paging.Paging;
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
	
	@GetMapping("/admin/contacts/list")
	public String showListContact(Model theModel, @RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit) {
		
		ContactDTO model = new ContactDTO();
		
		IPaging paging = new Paging(page, limit);
		
		Iterable<ContactDTO> theContactDto = contactService.findAll(paging);
		
		theModel.addAttribute("contacts", theContactDto);
		
		model.setPage(page);
		model.setLimit(limit);
		model.setTotalItem(contactService.count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / paging.getLimit()));
		
		
		
		theModel.addAttribute("model", model);
		
		
		return "admin/contact/list-contact";
	}
	
	@GetMapping("/admin/contacts/{id}")
	public String showContactDetail(Model theModel, @PathVariable("id") Long theId) {
		
		ContactDTO theContactDTO = contactService.findById(theId);
		
		theModel.addAttribute("contact", theContactDTO);
		
		
		return "admin/contact/contact-detail";
	}
	
	@DeleteMapping("/admin/contacts/")
	@ResponseBody  // response to page success or error
	public void deleteContacts(@RequestBody Long[] ids) {
		try {
			for(Long id:ids) {
				contactService.remove(id);
			}
		} catch(Exception e) {
			e.printStackTrace();
		
		}
		
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("theSearchValue") String theSearchValue, Model theModel) {
		
		ContactDTO model = new ContactDTO();
		
		IPaging paging = new Paging(null, null);
		
		Iterable<ContactDTO> theContactDto = contactService.search(paging, theSearchValue);
		
		theModel.addAttribute("contacts", theContactDto);
		
		model.setPage(1);
		model.setLimit(100);
		model.setTotalItem(StreamSupport.stream(theContactDto.spliterator(), false).count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		
		
		theModel.addAttribute("model", model);
		
		return "admin/contact/list-contact";
	}


}
