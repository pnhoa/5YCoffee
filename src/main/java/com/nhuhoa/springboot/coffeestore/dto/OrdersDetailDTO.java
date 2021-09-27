package com.nhuhoa.springboot.coffeestore.dto;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.constraints.Min;

import com.nhuhoa.springboot.coffeestore.entity.Orders;
import com.nhuhoa.springboot.coffeestore.entity.Product;

public class OrdersDetailDTO extends AbstractDTO<OrdersDetailDTO> {
	
	@Min(1)
	private int quantity;
	
	private BigDecimal totalPrice;
	
	private int status;
	
	private Product product;
	
	private Orders orders;
	
	private Long productId;
	
	private String statusMsg;
	
	private Map<Integer, String> statusMap;
	

	public OrdersDetailDTO() {
	}

	public OrdersDetailDTO(@Min(1) int quantity, BigDecimal totalPrice) {
		this.quantity = quantity;
		this.totalPrice = totalPrice;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public Map<Integer, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<Integer, String> statusMap) {
		this.statusMap = statusMap;
	}
	
	

}
