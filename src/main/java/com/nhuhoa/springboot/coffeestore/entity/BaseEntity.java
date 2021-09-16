package com.nhuhoa.springboot.coffeestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name ="createddate")
	@CreatedDate
	private Date createdDate;
	
	@Column(name = "modifieddate")
	@LastModifiedDate
	private Date modifiedDate;
	
	@Column(name = "createdby")
	@CreatedBy
	private String createdBy;
	
	@Column(name = "modifiedby")
	@LastModifiedBy
	private String modefiedBy;

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public String getModefiedBy() {
		return modefiedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setModefiedBy(String modefiedBy) {
		this.modefiedBy = modefiedBy;
	}

	public BaseEntity(Date createdDate, Date modifiedDate, String createdBy, String modefiedBy) {
		super();
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modefiedBy = modefiedBy;
	}

	public BaseEntity() {
		super();
	}
	
		
}
