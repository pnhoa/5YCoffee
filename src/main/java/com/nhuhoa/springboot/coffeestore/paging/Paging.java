package com.nhuhoa.springboot.coffeestore.paging;

public class Paging implements IPaging {
	
	private Integer page;
	private Integer maxPageItem;
	
	public Paging(Integer page, Integer maxPageItem) {
		this.maxPageItem = maxPageItem;
		this.page = page;

	}
	

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		if(this.maxPageItem != null && this.maxPageItem != null) {
			return (this.page - 1) * this.maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		// TODO Auto-generated method stub
		return this.maxPageItem;
	}

}
