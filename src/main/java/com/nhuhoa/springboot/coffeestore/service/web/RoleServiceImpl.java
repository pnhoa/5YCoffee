package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.dao.IRoleDao;
import com.nhuhoa.springboot.coffeestore.entity.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	public Map<String, String> findAll() {
		Map<String, String> result = new HashMap<String, String>();
		Iterable<Role> entities = roleDao.findAll();
		for(Role item: entities) {
			result.put(item.getCode(),item.getName());
		}
		
		result.remove("ROLE_CUSTOMER");
		
		return result;
	}

}
