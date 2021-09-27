package com.nhuhoa.springboot.coffeestore.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckDateValidator implements ConstraintValidator<CheckDateFormat, String> {

	private String pattern;

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        try {
            @SuppressWarnings("unused")
			Date date = new SimpleDateFormat(pattern).parse(object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
