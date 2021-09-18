package com.nhuhoa.springboot.coffeestore.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.dto.ProductDTO;

public class Cart {
	
	private int orderNum;
	
	private CustomerDTO customer;
	
	private final List<CartItem> cartItems = new ArrayList<CartItem>();
	
	private BigDecimal totalPrice;

	public Cart() {
		
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
	
	

	public BigDecimal getTotalPrice() {
		
		BigDecimal total = BigDecimal.ZERO;
		
		for(CartItem item : this.cartItems) {
			 
			total = total.add(item.getAmount());
		}
		
		return total;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	private CartItem findItemById(Long id) {
		
		for(CartItem item : this.cartItems) {
			if(item.getProduct().getId() == id) {
				return item;
			}
		}
		
		return null;
	}
	
	public void addProduct(ProductDTO product, int quantity) {
		
		CartItem item = this.findItemById(product.getId());
		
		if(item == null) {
			item = new CartItem();
			item.setQuantity(0);
			item.setProduct(product);
			this.cartItems.add(item);
		}
		
		int newQuantity = item.getQuantity() + quantity;
		
		if(newQuantity <= 0) {
			this.cartItems.remove(item);
		} else {
			item.setQuantity(newQuantity);
		}
	}
	
	public void updateProduct(Long id, int quantity) {
		
		CartItem item = this.findItemById(id);
		
		if(item != null) {
			if(quantity <= 0) {
				this.cartItems.remove(item);
			} else {
				item.setQuantity(quantity);
			}
		}
	}
	
	public void removeProduct(ProductDTO product) {
		
		CartItem item = this.findItemById(product.getId());
		
		if(item != null) {
			this.cartItems.remove(item);
		}
	}
	
	public boolean isEmpty() {
		
		return this.cartItems.isEmpty();
	}
	
	public int getQuatityTotal() {
		int quantity = 0;
		
		for(CartItem item : this.cartItems) {
			quantity += item.getQuantity();
		}
		
		return quantity;
	}
	
	public int getCartItemNum() {
		return this.cartItems.size();
	}
	
	public BigDecimal getAmountTotal() {
		
		BigDecimal total = BigDecimal.ZERO;
		
		for(CartItem item : this.cartItems) {
			 
			total = total.add(item.getAmount());
		}
		
		return total;
	}
	
	public void updateQuatity(Cart cart) {
		
		if(cart != null) {
			
			List<CartItem> items = cart.getCartItems();
			
			for(CartItem item : items) {
				this.updateProduct(item.getProduct().getId(), item.getQuantity());
			}
		}
	}

}
