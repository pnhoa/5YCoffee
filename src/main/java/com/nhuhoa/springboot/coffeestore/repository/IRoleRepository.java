package com.nhuhoa.springboot.coffeestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
	
	
	public Role findByCode(String theCode);

}
