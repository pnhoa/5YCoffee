package com.nhuhoa.springboot.coffeestore.entity;

import java.math.BigDecimal;

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
	
	@Column(name = "quantity", columnDefinition = "default 1")
	private int quantity;
	
	@Column(name = "total_price")
	private BigDecimal totalPrice;
	
	@Column(name = "status", columnDefinition = "default 0")
	private int status;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}
