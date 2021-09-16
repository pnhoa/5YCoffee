package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.Map;

import com.nhuhoa.springboot.coffeestore.dto.CategoryDTO;
import com.nhuhoa.springboot.coffeestore.entity.Category;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

public interface CategoryService extends GeneralService<CategoryDTO> {
	
	Map<String, String> findAllReturnMap(IPaging paging);
	
	Category findOneByCode(String theCode);

}
