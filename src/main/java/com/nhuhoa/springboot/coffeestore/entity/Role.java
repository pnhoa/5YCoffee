package com.nhuhoa.springboot.coffeestore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

	@Column(name = "code", unique = true, nullable = false)
	private String code;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<User>();

	@OneToMany(mappedBy = "role")
	private List<Customer> customers = new ArrayList<Customer>();

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

	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	

	

}
