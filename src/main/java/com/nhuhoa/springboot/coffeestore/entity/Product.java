package com.nhuhoa.springboot.coffeestore.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name = "short_description", columnDefinition = "TEXT")
	private String shortDescription;
	
	@Column(name = "description",columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<OrdersDetail> ordersDetail = new ArrayList<OrdersDetail>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<OrdersDetail> getOrderDetail() {
		return ordersDetail;
	}

	public void setOrderDetail(List<OrdersDetail> orderDetail) {
		this.ordersDetail = orderDetail;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	
	
	
	
}
