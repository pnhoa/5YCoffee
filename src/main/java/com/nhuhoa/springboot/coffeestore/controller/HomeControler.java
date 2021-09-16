package com.nhuhoa.springboot.coffeestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhuhoa.springboot.coffeestore.dto.CategoryDTO;
import com.nhuhoa.springboot.coffeestore.dto.ProductDTO;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.service.web.CategoryService;
import com.nhuhoa.springboot.coffeestore.service.web.ProductService;

@Controller
public class HomeControler {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value= {"/", ""})
	public String showHomePage() {
		return "web/home";
	}
	
	@GetMapping("/services")
	public String showServicesPage() {
		
		return "web/services";
	}
	

	
	@GetMapping("/menu")
	public String showMenuPage(Model theModel) {
		
		Iterable<CategoryDTO> categories = categoryService.findAll(new IPaging() {
			
			@Override
			public Integer getPage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Integer getOffset() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Integer getLimit() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		
		theModel.addAttribute("categories", categories);
		
		return "web/menu";
	}
	
	@GetMapping("/product")
	public String showProduct(@RequestParam("id") Long Theid, Model theModel) {
		
		ProductDTO theProduct = productService.findById(Theid);
		
		Iterable<ProductDTO> theProducts = productService.findByCategoryId(theProduct.getCategory().getId());
		
		theModel.addAttribute("product", theProduct);
		theModel.addAttribute("products", theProducts);
		
		return "web/one-product";
	}
}
