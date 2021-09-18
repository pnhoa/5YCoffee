package com.nhuhoa.springboot.coffeestore.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nhuhoa.springboot.coffeestore.model.Cart;

public class CartUtils {
	
	public static Cart getCartInSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Cart theCart = (Cart) session.getAttribute("myCart");
		
		if(theCart == null) {
			theCart = new Cart();
			
			session.setAttribute("myCart", theCart);
		}
		
		return theCart;
	}
	
	public static Integer getCartItemNum(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Cart theCart = (Cart) session.getAttribute("myCart");
		
		Integer theCartItemNum = (Integer) session.getAttribute("cartItemNum");
		
		if( theCartItemNum == null) {
			
			theCartItemNum = theCart.getCartItemNum();
			
			session.setAttribute("cartItemNum", theCartItemNum);
		}
		
		return theCartItemNum;
	}
	
	public static void removeCartInSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("myCart");
	}
	
	public static void storeLastOrderedCartInSession(HttpServletRequest request, Cart theCart) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("lastOrderedCart", theCart);
	}
	
	public static Cart getLastOrderedCartInSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		return (Cart) session.getAttribute("lastOrderedCart");
	}

}
