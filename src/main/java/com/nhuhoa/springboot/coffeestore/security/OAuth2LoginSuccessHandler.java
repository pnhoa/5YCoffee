package com.nhuhoa.springboot.coffeestore.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.service.web.CustomerService;
import com.nhuhoa.springboot.coffeestore.utils.Provider;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private CustomerService customerService;
	
	public Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		CustomOAuth2User oAuth2User  = (CustomOAuth2User) authentication.getPrincipal();
		
		String name = oAuth2User.getName();
		
		String email = oAuth2User.getEmail();
		
		CustomerDTO theCustomerDto = customerService.findByEmail(email);
		
		if(theCustomerDto == null) {
			theCustomerDto = customerService.createCustomerAfteOAuthLoginSuccess(name, email, Provider.GOOGLE);
		}
		
		
		logger.info(">>>> Email: " + email);
		
		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("customer", theCustomerDto);
		
		logger.info(">>>> Role: " + theCustomerDto.getRole().getCode());

		// forward to home page

		response.sendRedirect(request.getContextPath() + "/");
	}

	

}
