package com.nhuhoa.springboot.coffeestore.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.model.Cart;
import com.nhuhoa.springboot.coffeestore.service.web.CustomerService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private CustomerService customerService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String userName = authentication.getName();

		CustomerDTO theCustomer = customerService.findByUserName(userName);
		
		Cart theCart = new Cart();

		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("customer", theCustomer);
		session.setAttribute("myCart", theCart);
		session.setAttribute("cartItemNum", theCart.getCartItemNum());
		

		// forward to home page

		response.sendRedirect(request.getContextPath() + "/");

	}

}
