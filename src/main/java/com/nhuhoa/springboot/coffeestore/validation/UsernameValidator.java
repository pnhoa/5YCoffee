package com.nhuhoa.springboot.coffeestore.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

	private Pattern pattern;
	private Matcher matcher;
	
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,}$";
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		pattern = Pattern.compile(USERNAME_PATTERN);
		if(username == null) {
			return false;
		}
		matcher = pattern.matcher(username);
		return matcher.matches();
	}

}
