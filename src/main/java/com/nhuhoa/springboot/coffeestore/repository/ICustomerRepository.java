package com.nhuhoa.springboot.coffeestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
