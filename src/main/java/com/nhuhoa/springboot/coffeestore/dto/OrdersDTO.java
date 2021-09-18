package com.nhuhoa.springboot.coffeestore.dto;

import java.math.BigDecimal;

import com.nhuhoa.springboot.coffeestore.entity.Customer;

public class OrdersDTO extends AbstractDTO<OrdersDTO>{
	
	private BigDecimal totalPrice;
	
	private String note;
	
	private int orderNum;
	
	private String address;
	
	private Customer customer;

	public OrdersDTO() {
		
	}

	public OrdersDTO(BigDecimal totalPrice, String note) {
		this.totalPrice = totalPrice;
		this.note = note;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
