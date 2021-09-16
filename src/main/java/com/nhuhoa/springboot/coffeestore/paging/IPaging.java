package com.nhuhoa.springboot.coffeestore.paging;

public interface IPaging {
	
	Integer getPage();
	Integer getOffset();
	Integer getLimit();

}
