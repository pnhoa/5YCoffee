package com.nhuhoa.springboot.coffeestore.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

	public Map<String, String> getMessage(String message){
		Map<String, String> result = new HashMap<>();
		if(message.equals("save_employee_success")) {
			result.put("message", "Saving an employee successfully!");
			result.put("alert","success");
		} else if(message.equals("delete_success")) {
				result.put("message", "Delete Success");
				result.put("alert","success");
		} else if(message.equals("error_system")) {
			result.put("message", "Error System");
			result.put("alert","danger");
		} else if(message.equals("employee_not_found")) {
			result.put("message", "Employee not found");
			result.put("alert","danger");
		}
		return result;
	}
}
