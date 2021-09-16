package com.nhuhoa.springboot.coffeestore.service.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.entity.Role;
import com.nhuhoa.springboot.coffeestore.repository.IRoleRepository;

@Service
@Transactional
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public Iterable<Role> findAll() {
		
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Long id) {
		
		return roleRepository.findById(id);
	}

	@Override
	public Role save(Role t) {
		
		return roleRepository.save(t);
	}

	@Override
	public void remove(Long id) {
		
		roleRepository.deleteById(id);
		
	}

	@Override
	public Role findByCode(String theCode) {
		return roleRepository.findByCode(theCode);
	}

	

}
