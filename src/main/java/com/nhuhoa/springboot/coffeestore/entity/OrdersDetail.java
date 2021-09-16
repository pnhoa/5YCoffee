package com.nhuhoa.springboot.coffeestore.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders_detail")
public class OrdersDetail extends BaseEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders_id")
	private Orders orders;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "number", columnDefinition = "default 1")
	private int number;
	
	@Column(name = "total_price")
	private BigDecimal totalPrice;

	public Orders getOrder() {
		return orders;
	}

	public void setOrder(Orders order) {
		this.orders = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
