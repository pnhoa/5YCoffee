package com.nhuhoa.springboot.coffeestore.dto;

public class RoleDTO extends AbstractDTO<RoleDTO> {

	private String code;
	
	private String name;

	public RoleDTO() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
