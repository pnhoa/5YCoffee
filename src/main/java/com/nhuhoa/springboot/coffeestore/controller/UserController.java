package com.nhuhoa.springboot.coffeestore.controller;

import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhuhoa.springboot.coffeestore.dto.UserDTO;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.paging.Paging;
import com.nhuhoa.springboot.coffeestore.service.web.RoleService;
import com.nhuhoa.springboot.coffeestore.service.web.UserService;
import com.nhuhoa.springboot.coffeestore.utils.MessageUtil;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/list")
	public String showListEmployee(Model theModel, @RequestParam(value="page", defaultValue = "1") int page,
								@RequestParam(value="limit", defaultValue = "5") int limit,
								@RequestParam(value="message", required = false) String message) {
		
		UserDTO model = new UserDTO();
		
		IPaging paging = new Paging(page, limit);
		
		Iterable<UserDTO> theUserDto = userService.findAll(paging);
		
		theModel.addAttribute("users", theUserDto);
		
		model.setPage(page);
		model.setLimit(limit);
		model.setTotalItem(userService.count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / paging.getLimit()));
		
		if(message != null) {
			Map<String, String> mess =  messageUtil.getMessage(message);
			model.setAlert(mess.get("alert"));
			model.setMessage(mess.get("message"));
		}
		
		theModel.addAttribute("model", model);
		
		return "admin/user/list-user";
	}
	
	@PostMapping("/edit")
	public String showEmployeeEditForm(Model theModel, @ModelAttribute(name = "user") UserDTO theOldUser) {
		
		UserDTO theUserDto = new UserDTO();
		
		if(theOldUser.getId() != null) {
			theUserDto = userService.findById(theOldUser.getId());
			
			if(theUserDto == null) {
				
				return "redirect:list?message=employee_not_found";
			}
		}
		
		theModel.addAttribute("user", theUserDto);
		
		theModel.addAttribute("roles", roleService.findAll());
		
		return "admin/user/user-form";
	}
	

	
	@GetMapping("/add")
	public String showEmployeeAddForm(Model theModel, @ModelAttribute(name = "user") UserDTO theOldUser) {
		
		UserDTO theUserDto = new UserDTO();
		
		
		theModel.addAttribute("user", theUserDto);
		
		theModel.addAttribute("roles", roleService.findAll());
		
		return "admin/user/user-form";
	}
	
	@PostMapping("/save")
	public String processSavingUser(@Valid @ModelAttribute("user") UserDTO theUserDto,
								BindingResult theBindingResult, Model theModel) {
		
		String userName = theUserDto.getUserName();
		String email = theUserDto.getEmail();
		logger.info(">>> Processing add employee with username: " + userName);
		logger.info(">>> Email: " + email);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 
			 	theModel.addAttribute("user", theUserDto);
				
				theModel.addAttribute("roles", roleService.findAll());
			 
			 return "admin/user/user-form";
	        }

		// check create or update
		 if(theUserDto.getId() == null) {
			// check the database if user already exists
			 UserDTO existingUsername = userService.findByUserName(userName);
			 
			 if(existingUsername != null) {
				 
				 	theModel.addAttribute("user", new UserDTO());
				 	theModel.addAttribute("roles", roleService.findAll());
					theModel.addAttribute("registrationError", "Username already exists.");

					logger.warning("Username already exists.");
		        	return "admin/user/user-form";
			 }
			 
			// check the database if email already exists
			 UserDTO existingEmail = userService.findByEmail(email);
			 if(existingEmail != null) {
				 
				 	theModel.addAttribute("user", new UserDTO());
				 	theModel.addAttribute("roles", roleService.findAll());
					theModel.addAttribute("registrationError", "Email already exists.");

					logger.warning("Email already exists.");
		        	return "admin/user/user-form";
			 }
			 
			 if(theUserDto.getPassword() == null || theUserDto.getPassword().trim().length() == 0) {
				 	theModel.addAttribute("user", theUserDto);
				 	theModel.addAttribute("roles", roleService.findAll());
					theModel.addAttribute("registrationError", "Password not contain white space.");

					logger.warning("Password not contain white space.");
		        	return "admin/user/user-form";
			 }
		 }
		
		 String theOldPassword = theUserDto.getTheOldPassword();
		 if( theOldPassword != null && theOldPassword.length() > 0) {
			 UserDTO theOldUserDto = userService.findById(theUserDto.getId());
			 
			 if(!passwordEncoder.matches(theOldPassword, theOldUserDto.getPassword())) {
				 	theModel.addAttribute("user", theUserDto);
				 	theModel.addAttribute("roles", roleService.findAll());
					theModel.addAttribute("registrationError", "The old password wrong.");

					logger.warning("The old password wrong.");
		        	return "admin/user/user-form";
			 }
		 }
		 
		 userService.save(theUserDto);
		 
		 logger.info("Successfully created customer: " + userName);
		
		return "redirect:list?message=save_employee_success";
	}
	
	
	@GetMapping("/{id}")
	public String showUserDetail(Model theModel, @PathVariable("id") Long id) {
		
		UserDTO theUserDto = userService.findById(id);
		
		theModel.addAttribute("user", theUserDto);
		
		return "admin/user/user-detail";
	}
	
	@PostMapping("/")
	@ResponseBody  // response to page success or error
	public void deleteUsers(@RequestBody Long[] ids) {
		try {
			for(Long id:ids) {
				userService.remove(id);
			}
		} catch(Exception e) {
			e.printStackTrace();
		
		}
		
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("theSearchValue") String theSearchValue, Model theModel) {
		UserDTO model = new UserDTO();
		
		IPaging paging = new Paging(null, null);
		
		Iterable<UserDTO> theUserDto = userService.search(paging, theSearchValue);
		
		theModel.addAttribute("users", theUserDto);
		
		model.setPage(1);
		model.setLimit(100);
		model.setTotalItem(StreamSupport.stream(theUserDto.spliterator(), false).count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		
		
		theModel.addAttribute("model", model);
		
		return "admin/user/list-user";
	}
	
	@GetMapping("/roles")
	public String changeRoles(Model theModel, @RequestParam(value = "message", required = false) String message) {
		
		UserDTO theUserDto = new UserDTO();
		
		if(message != null) {
			Map<String, String> mess =  messageUtil.getMessage(message);
			theUserDto.setAlert(mess.get("alert"));
			theUserDto.setMessage(mess.get("message"));
		}
		
		theModel.addAttribute("user", theUserDto);
		theModel.addAttribute("roles",roleService.findAll());
		
		return "admin/user/roles";
	}
	
	@PostMapping("/change-roles")
	public String processingChangeRoles(@ModelAttribute("user") UserDTO theUserDto) {
		
		String theEmail = theUserDto.getEmail();
		
		UserDTO theUserDtoDB = userService.findByEmail(theEmail);
		
		if(theUserDtoDB ==  null) {
			
			return "redirect:roles?message=employee_not_found";
		}
		
		userService.saveRoles(theUserDto);
		
		return "redirect:roles?message=save_employee_success";
	}

}
