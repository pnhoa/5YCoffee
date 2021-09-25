package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.dao.ICategoryDao;
import com.nhuhoa.springboot.coffeestore.dao.IProductDao;
import com.nhuhoa.springboot.coffeestore.dto.CategoryDTO;
import com.nhuhoa.springboot.coffeestore.entity.Category;
import com.nhuhoa.springboot.coffeestore.entity.Product;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private ICategoryDao categoryDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Iterable<CategoryDTO> findAll(IPaging paging) {
		Iterable<Category> theCategories = categoryDao.findAll(paging); 
		Long i = 1L;
		for(Category tempCategory : theCategories) {
			tempCategory.setProducts((List<Product>) productDao.findByCategoryId(i));
			i++;
		}
		
		TypeToken<Iterable<CategoryDTO>> typeToken = new TypeToken<Iterable<CategoryDTO>>() {
		};

		Iterable<CategoryDTO> theCategoryDTOs = mapper.map(theCategories, typeToken.getType());
		
		
		
		return theCategoryDTOs;
	}

	@Override
	public CategoryDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO save(CategoryDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, String> findAllReturnMap(IPaging paging) {
		
		Map<String, String> result = new HashMap<String, String>();
		Iterable<Category> entities = categoryDao.findAll(paging);
		for(Category item: entities) {
			result.put(item.getCode(),item.getName());
		}
		return result;
	}

	@Override
	public Category findOneByCode(String theCode) {
		Category theCategory = categoryDao.findOneByCode(theCode);
		
		
		return theCategory;
	}

	@Override
	public Iterable<CategoryDTO> search(IPaging paging, String theSearchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
