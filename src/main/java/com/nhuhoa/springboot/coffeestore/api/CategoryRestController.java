package com.nhuhoa.springboot.coffeestore.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhuhoa.springboot.coffeestore.entity.Category;
import com.nhuhoa.springboot.coffeestore.service.api.ICategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryRestController {

	@Autowired
	private ICategoryService categoryService;

	@PostMapping
	public ResponseEntity<Category> createNewCategory(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Category>> getAllCategory() {
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id) {
		Optional<Category> categoryOptional = categoryService.findById(id);
		return categoryOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		Optional<Category> categoryOptional = categoryService.findById(id);
		return categoryOptional.map(category1 -> {
			category.setId(category1.getId());
			return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
		Optional<Category> categoryOptional = categoryService.findById(id);
		return categoryOptional.map(category -> {
			categoryService.remove(id);
			return new ResponseEntity<>(category, HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
