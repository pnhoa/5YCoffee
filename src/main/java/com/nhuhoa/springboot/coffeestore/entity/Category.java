package com.nhuhoa.springboot.coffeestore.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "category")
@JsonIgnoreProperties(value= {"products"})
public class Category extends BaseEntity {

	@Column(name = "code", columnDefinition = "varchar(50)", nullable = false)
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL)
	private List< Product> products = new ArrayList<Product>();

	public Category() {
		
	}

	public Category(Date createdDate, Date modifiedDate, String createdBy, String modefiedBy, String code,
			String name) {
		super(createdDate, modifiedDate, createdBy, modefiedBy);
		this.code = code;
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List< Product> products) {
		this.products = products;
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
