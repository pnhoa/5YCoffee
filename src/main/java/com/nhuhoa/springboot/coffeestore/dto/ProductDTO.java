package com.nhuhoa.springboot.coffeestore.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.nhuhoa.springboot.coffeestore.entity.Category;

public class ProductDTO extends AbstractDTO<ProductDTO> {
	
	@NotNull(message = "is required")
	private String name;
	
	private String thumbnail;
	
	private String shortDescription;
	
	private String description;
	
	@NotNull(message = "is required")
	@Min(0)
	private BigDecimal price;
	
	
	private Category category;
	
	private Long categoryId;
	
	@NotNull(message = "is required")
	private String categoryCode;
	
	// Upload file.
    private MultipartFile fileData;
    
    private String productThumbnailPath;

	public ProductDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public MultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getProductThumbnailPath() {
		return productThumbnailPath;
	}

	public void setProductThumbnailPath(String productThumbnailPath) {
		this.productThumbnailPath = productThumbnailPath;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	
	
	

}
