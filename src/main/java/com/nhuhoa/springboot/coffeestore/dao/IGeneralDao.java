package com.nhuhoa.springboot.coffeestore.dao;

import com.nhuhoa.springboot.coffeestore.paging.IPaging;

public interface IGeneralDao<T> {
	
	Iterable<T> findAll(IPaging paging);

    T findById(Long id);

    T save(T t);

    void remove(Long id);
    
    Long count();
    
    Iterable<T> search(IPaging paging, String theSearchValue);

}
