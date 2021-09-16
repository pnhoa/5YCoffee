package com.nhuhoa.springboot.coffeestore.service.web;

import com.nhuhoa.springboot.coffeestore.paging.IPaging;

public interface GeneralService<T> {
	
	Iterable<T> findAll(IPaging paging);

    T findById(Long id);

    T save(T t);

    void remove(Long id);
    
    Iterable<T> search(IPaging paging, String theSearchValue);

}
