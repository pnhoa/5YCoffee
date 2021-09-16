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

import com.nhuhoa.springboot.coffeestore.dto.UserDTO;
import com.nhuhoa.springboot.coffeestore.service.web.UserService;

@Component
public class CustomAdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String userName = authentication.getName();

		UserDTO theUser = userService.findByUserName(userName);

		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);

		// forward to home page

		response.sendRedirect(request.getContextPath() + "/admin/");

	}

}
