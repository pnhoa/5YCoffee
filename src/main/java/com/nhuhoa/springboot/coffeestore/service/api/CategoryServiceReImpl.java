package com.nhuhoa.springboot.coffeestore.service.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.entity.Category;
import com.nhuhoa.springboot.coffeestore.repository.ICategoryRepository;

@Service
@Transactional
public class CategoryServiceReImpl implements ICategoryService {
	
	@Autowired
    private ICategoryRepository categoryRepository;
	
	@Override
	public Iterable<Category> findAll() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		
		return categoryRepository.findById(id);
	}

	@Override
	public Category save(Category category) {
		
		return categoryRepository.save(category);
	}

	@Override
	public void remove(Long id) {
		categoryRepository.deleteById(id);

	}

}
