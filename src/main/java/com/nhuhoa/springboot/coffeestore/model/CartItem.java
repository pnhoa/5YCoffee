package com.nhuhoa.springboot.coffeestore.model;

import java.math.BigDecimal;

import com.nhuhoa.springboot.coffeestore.dto.ProductDTO;

public class CartItem {
	
	private ProductDTO product;
	
	private int quantity;
	
	private BigDecimal totalPrice;

	public CartItem(int quantity) {
		this.quantity = 0;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartItem() {
		
	}
	
	
	public BigDecimal getTotalPrice() {
		
		return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getAmount() {
		
		return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));

	}

}
