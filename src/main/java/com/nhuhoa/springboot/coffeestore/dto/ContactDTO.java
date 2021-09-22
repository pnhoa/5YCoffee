package com.nhuhoa.springboot.coffeestore.dto;

import javax.validation.constraints.NotNull;

import com.nhuhoa.springboot.coffeestore.validation.ValidEmail;

public class ContactDTO extends AbstractDTO<ContactDTO> {

	@NotNull(message = "is required")
	private String name;
	
	
	@ValidEmail
	@NotNull(message = "is required")
	private String email;
	
	
	private String subject;
	
	
	private String message;
	
	private String messageNo;


	public ContactDTO() {
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getMessageNo() {
		return messageNo;
	}


	public void setMessageNo(String messageNo) {
		this.messageNo = messageNo;
	}
	
	
}
