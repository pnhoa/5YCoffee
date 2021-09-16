package com.nhuhoa.springboot.coffeestore.dto;

import java.sql.Timestamp;

public abstract class AbstractDTO<T> {
	
	private Long id;
	
	private Timestamp createdDate;
	
	private Timestamp modifiedDate;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private Integer page;
	
	private Integer limit;
	
	private Integer totalPage;
	
	private Long totalItem;
	
	private String message;
	
	private String alert;
	

	public AbstractDTO() {
		
	}

	public AbstractDTO(Long id, Timestamp createdDate, Timestamp modifiedDate, String createdBy, String modifiedBy) {
		this.id = id;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Long totalItem) {
		this.totalItem = totalItem;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}
	
	

}
