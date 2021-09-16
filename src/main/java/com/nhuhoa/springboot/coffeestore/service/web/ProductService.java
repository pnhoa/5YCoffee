package com.nhuhoa.springboot.coffeestore.service.web;

import com.nhuhoa.springboot.coffeestore.dto.ProductDTO;

public interface ProductService extends GeneralService<ProductDTO> {

	Iterable<ProductDTO> findByCategoryId(Long categoryId);

	Long count();
	


}
