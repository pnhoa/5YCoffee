package com.nhuhoa.springboot.coffeestore.service.api;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.entity.Customer;
import com.nhuhoa.springboot.coffeestore.repository.ICustomerRepository;

@Service
@Transactional
public class CustomerService implements ICustomerService {
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired IRoleService roleService;

	@Override
	public Iterable<Customer> findAll() {
		
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> findById(Long id) {
		
		return customerRepository.findById(id);
	}

	@Override
	public Customer save(Customer customer) {
		
		customer.setRole(roleService.findByCode("ROLE_CUSTOMER"));
		if(customer.getId() == null) {
			customer.setCreatedDate(new Date());
			System.out.println("<<<<<" + Calendar.getInstance().getTime());
		} else {
			customer.setModifiedDate(Calendar.getInstance().getTime());
		}
		
		
		return customerRepository.save(customer);
	}

	@Override
	public void remove(Long id) {
		
		customerRepository.deleteById(id);

	}

}
