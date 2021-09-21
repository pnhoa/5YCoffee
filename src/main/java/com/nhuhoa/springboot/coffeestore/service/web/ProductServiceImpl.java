package com.nhuhoa.springboot.coffeestore.service.web;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.dao.ICategoryDao;
import com.nhuhoa.springboot.coffeestore.dao.IProductDao;
import com.nhuhoa.springboot.coffeestore.dto.ProductDTO;
import com.nhuhoa.springboot.coffeestore.entity.Product;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private ICategoryDao categoryDao;
	
	@Autowired
	public ModelMapper mapper;

	@Override
	public Iterable<ProductDTO> findAll(IPaging paging) {
		
		Iterable<Product> theProducts = productDao.findAll(paging); 
		
		TypeToken<Iterable<ProductDTO>> typeToken = new TypeToken<Iterable<ProductDTO>>() {
		};

		Iterable<ProductDTO> theProductDTOs = mapper.map(theProducts, typeToken.getType());
		
		return theProductDTOs;
	}

	@Override
	public ProductDTO findById(Long id) {
		
		Product theProduct = productDao.findById(id);
		if(theProduct == null) {
			return null;
		}
		
		ProductDTO theProductDTO = mapper.map(theProduct, ProductDTO.class);
		
		theProductDTO.setCategoryCode(theProduct.getCategory().getCode());
		
		return theProductDTO;
	}

	@Override
	public ProductDTO save(ProductDTO theProductDTO) {
		
		Product theProduct = mapper.map(theProductDTO, Product.class);
		
		if(theProductDTO.getCategoryCode() == null) {
			theProductDTO.setCategoryCode("LOAI-KHAC");
		}
		
		theProduct.setCategory(categoryDao.findOneByCode(theProductDTO.getCategoryCode()));
		
		Product theProductNew =   productDao.save(theProduct);
		
		theProductDTO.setId(theProductNew.getId());
		
		return theProductDTO;
	}

	@Override
	@Transactional
	public void remove(Long id) {
		
		productDao.remove(id);

	}

	@Override
	public Iterable<ProductDTO> findByCategoryId(Long categoryId) {
		
		Iterable<Product> theProducts = productDao.findByCategoryId(categoryId);
		
		TypeToken<Iterable<ProductDTO>> typeToken = new TypeToken<Iterable<ProductDTO>>() {
		};

		Iterable<ProductDTO> theProductDTOs = mapper.map(theProducts, typeToken.getType());
		
		return theProductDTOs;
	}

	@Override
	public Iterable<ProductDTO> search(IPaging paging, String theSearchValue) {
		
		Iterable<Product> theProducts = productDao.search(paging, theSearchValue);
		
		TypeToken<Iterable<ProductDTO>> typeToken = new TypeToken<Iterable<ProductDTO>>() {
		};

		Iterable<ProductDTO> theProductDTOs = mapper.map(theProducts, typeToken.getType());
		
		return theProductDTOs;
	}

	@Override
	public Long count() {
		
		return productDao.count();
	}

}
