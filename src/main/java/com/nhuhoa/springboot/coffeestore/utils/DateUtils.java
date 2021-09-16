package com.nhuhoa.springboot.coffeestore.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static Date stringToDate(String dateString) {

		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Date birthday = new Date();
		try {
			 birthday = sourceFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return birthday;
	}
	
	public static String dateToString(Date theDate) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String dateString = null;
			dateString = formatter.format(theDate);
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return dateString;
	}

}
