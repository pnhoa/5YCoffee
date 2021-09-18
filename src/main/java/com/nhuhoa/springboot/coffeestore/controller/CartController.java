package com.nhuhoa.springboot.coffeestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhuhoa.springboot.coffeestore.dto.ProductDTO;
import com.nhuhoa.springboot.coffeestore.model.Cart;
import com.nhuhoa.springboot.coffeestore.model.CartItem;
import com.nhuhoa.springboot.coffeestore.service.web.ProductService;
import com.nhuhoa.springboot.coffeestore.utils.CartUtils;

@Controller
@RequestMapping("/checkout")
public class CartController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/cart")
	public String showCartPage(HttpServletRequest request, HttpSession session) {
		
		Cart theCart = CartUtils.getCartInSession(request);
		
		session.setAttribute("myCartItems", theCart.getCartItems());
		session.setAttribute("cartItemNum", theCart.getCartItemNum());
		
		return "web/cart";
	}

	@PostMapping("/addProduct")
	public String addProductToCart(@RequestParam("id") Long id, @RequestParam("quantity") int quantity, HttpServletRequest request, HttpSession session ) {
		
		ProductDTO theProduct = productService.findById(id);
		
		if(theProduct != null) {
			Cart theCart = CartUtils.getCartInSession(request);
			
			theCart.addProduct(theProduct, quantity);
			
			session.setAttribute("myCart", theCart);
		}
		
		return "redirect:cart";
	}
	
	@PostMapping("/updateCart")
	public String updateCart(HttpServletRequest request, HttpSession session ) {
			
			Cart theCartSession = CartUtils.getCartInSession(request);
			
			String [] quantity = request.getParameterValues("quantity");
			
			List<CartItem> cartItems = theCartSession.getCartItems();
			
			int i = 0 ;
			for(CartItem cartItem: cartItems) {
				cartItem.setQuantity(Integer.parseInt(quantity[i]));
				i++;
			}
			
			
			session.setAttribute("myCartItems", cartItems);
			session.setAttribute("cartItemNum", theCartSession.getCartItemNum());
			
			return "redirect:cart";
	}
	
	@GetMapping("/remove/{id}")
	public String removeCartItem(@PathVariable("id") String idString, HttpServletRequest request, HttpSession session) {
		
		Long id = Long.parseLong(idString);
		
		ProductDTO theProduct = productService.findById(id);
		
		if(theProduct != null) {
			Cart theCart = CartUtils.getCartInSession(request);
			
			theCart.removeProduct(theProduct);
			
			session.setAttribute("myCart", theCart);
			session.setAttribute("myCartItems", theCart.getCartItems());
			session.setAttribute("cartItemNum", theCart.getCartItemNum());
		}
		
		
		
		return "web/cart"; 
	}
	
	@GetMapping("/payment")
	public String showPaymentPage() {
		
		
		return "web/payment";
	}
	
}
