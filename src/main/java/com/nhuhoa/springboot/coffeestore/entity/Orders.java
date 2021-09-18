package com.nhuhoa.springboot.coffeestore.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders") // error with name : 'order'
public class Orders extends BaseEntity {
	
	@Column(name = "total_price", columnDefinition = "default 0.0")
	private BigDecimal totalPrice;

	@Column(name = "note")
	private String note;
	
	@Column(name = "address")
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "orders")
	private List<OrdersDetail> ordersDetail = new ArrayList<OrdersDetail>();

	

	public List<OrdersDetail> getOrderDetail() {
		return ordersDetail;
	}

	public void setOrderDetail(List<OrdersDetail> orderDetail) {
		this.ordersDetail = orderDetail;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrdersDetail> getOrdersDetail() {
		return ordersDetail;
	}

	public void setOrdersDetail(List<OrdersDetail> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}
	

}
