package com.nhuhoa.springboot.coffeestore.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhuhoa.springboot.coffeestore.entity.Customer;
import com.nhuhoa.springboot.coffeestore.service.api.ICustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customers")
public class CustomerRestController {

	@Autowired
	private ICustomerService customerService;
	
	

	@PostMapping
	public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Customer>> getAllCustomer() {
		return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		Optional<Customer> customerOptional = customerService.findById(id);
		return customerOptional.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updatecustomer(@PathVariable Long id, @RequestBody Customer customer) {
		Optional<Customer> customerOptional = customerService.findById(id);
		return customerOptional.map(customer1 -> {
			customer.setId(customer1.getId());
			return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deletecustomer(@PathVariable Long id) {
		Optional<Customer> customerOptional = customerService.findById(id);
		return customerOptional.map(customer -> {
			customerService.remove(id);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/")
	public void deleteCustomer(@RequestBody Long[] ids) {
		
	}

}
