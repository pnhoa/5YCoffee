package com.nhuhoa.springboot.coffeestore.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

public class OrdersDetailDTO extends AbstractDTO<OrdersDetailDTO> {
	
	@Min(1)
	private int number;
	
	private BigDecimal totalPrice;

	public OrdersDetailDTO() {
	}

	public OrdersDetailDTO(@Min(1) int number, BigDecimal totalPrice) {
		this.number = number;
		this.totalPrice = totalPrice;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
