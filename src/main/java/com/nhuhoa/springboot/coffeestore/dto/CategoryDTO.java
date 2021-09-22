package com.nhuhoa.springboot.coffeestore.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class CategoryDTO extends AbstractDTO<CategoryDTO> {

	@NotNull(message = "is required")
	private String code;

	@NotNull(message = "is required")
	private String name;
	
	private List< ProductDTO> products = new ArrayList<ProductDTO>();

	public CategoryDTO() {

	}

	public CategoryDTO(String code, String name) {
		this.code = code;
		this.name = name;
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

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}
