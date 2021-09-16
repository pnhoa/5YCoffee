package com.nhuhoa.springboot.coffeestore.dto;

import java.math.BigDecimal;

public class OrdersDTO extends AbstractDTO<OrdersDTO>{
	
	private BigDecimal totalPrice;
	
	private String note;

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

}
