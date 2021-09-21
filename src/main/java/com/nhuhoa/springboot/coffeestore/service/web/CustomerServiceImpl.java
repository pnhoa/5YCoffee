package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.dao.ICustomerDao;
import com.nhuhoa.springboot.coffeestore.dao.IRoleDao;
import com.nhuhoa.springboot.coffeestore.dto.CustomerDTO;
import com.nhuhoa.springboot.coffeestore.entity.Customer;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.utils.DateUtils;
import com.nhuhoa.springboot.coffeestore.utils.Provider;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Autowired 
	private IRoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Iterable<CustomerDTO> findAll(IPaging paging) {
		
		Iterable<Customer> theCustomers = customerDao.findAll(paging); 
		
		TypeToken<Iterable<CustomerDTO>> typeToken = new TypeToken<Iterable<CustomerDTO>>() {
		};

		Iterable<CustomerDTO> theCustomerDTOs = mapper.map(theCustomers, typeToken.getType());
		
		return theCustomerDTOs;
	}

	@Override
	public CustomerDTO findById(Long id) {
		
		Customer theCustomer = customerDao.findById(id);
		
		if(theCustomer == null) {
			return null;
		}
		
		CustomerDTO theCustomerDTO = mapper.map(theCustomer, CustomerDTO.class);
		
		// set birthday String display in view
		Date birthday = theCustomerDTO.getBirthday();	
		String birthdayString = null;
		if(birthday != null) {
			birthdayString = DateUtils.dateToString(birthday);
		}
		theCustomerDTO.setBirthdayString(birthdayString);	
		
		theCustomerDTO.setBirthdayString(birthdayString);
		
		return theCustomerDTO;
	}

	@Override
	public CustomerDTO save(CustomerDTO theCustomerDTO) {
		
		String birthdayString = theCustomerDTO.getBirthdayString();
		
		Date birthday = null;
		
		if(birthdayString != null && birthdayString.trim().length()>0 ) {
			
			birthday = DateUtils.stringToDate(birthdayString);
		}
		
		theCustomerDTO.setBirthday(birthday);
		
		theCustomerDTO.setEnabled(1);
		
		Customer theCustomer = mapper.map(theCustomerDTO, Customer.class);
		
		if(theCustomer.getId() == null) {
			theCustomer.setCreatedDate(Calendar.getInstance().getTime());
		} else {
			theCustomer.setModifiedDate(Calendar.getInstance().getTime());
		}
		theCustomer.setRole(roleDao.findRoleByCode("ROLE_CUSTOMER"));
		theCustomer.setPassword(passwordEncoder.encode(theCustomerDTO.getPassword()));
		theCustomer.setProvider(Provider.LOCAL);
		
		Customer theCustomerNew = customerDao.save(theCustomer);
		
		theCustomerDTO.setId(theCustomerNew.getId());
		theCustomerDTO.setEnabled(1);
		
		
		return theCustomerDTO;
	}

	@Override
	@Transactional
	public void remove(Long id) {
		
		customerDao.remove(id);

	}

	@Override
	public CustomerDTO findByUserName(String userName) {
		
		Customer theCustomer = customerDao.findByUserName(userName);
		
		if(theCustomer == null) {
			return null;
		}
		
		CustomerDTO theCustomerDTO = mapper.map(theCustomer, CustomerDTO.class);
		
		// set birthday String display in view
		Date birthday = theCustomerDTO.getBirthday();	
		String birthdayString = null;
		if(birthday != null) {
			birthdayString = DateUtils.dateToString(birthday);
		}
		theCustomerDTO.setBirthdayString(birthdayString);	
		
		theCustomerDTO.setBirthdayString(birthdayString);
		
		return theCustomerDTO;
	}
	
	@Override
	public CustomerDTO findByEmail(String email) {
		
		Customer theCustomer = customerDao.findByEmail(email);
		
		if(theCustomer == null) {
			return null;
		}
		
		CustomerDTO theCustomerDTO = mapper.map(theCustomer, CustomerDTO.class);
		
		// set birthday String display in view
		Date birthday = theCustomerDTO.getBirthday();	
		String birthdayString = null;
		if(birthday != null) {
			birthdayString = DateUtils.dateToString(birthday);
		}
		theCustomerDTO.setBirthdayString(birthdayString);	
		
		theCustomerDTO.setBirthdayString(birthdayString);

		return theCustomerDTO;
	}
	
	@Override
	@Transactional
	 public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = customerDao.findByUserName(userName);
 
        if (customer == null || customer.getEnabled() == 0) {
            throw new UsernameNotFoundException("Customer " + userName + " was not found in the database");
        }
 
        // [ROLE_USER, ROLE_ADMIN,..]
        String roleName = customer.getRole().getCode();
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleName != null) {
           
                GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
                grantList.add(authority);
        
        }
 
        UserDetails userDetails = (UserDetails) new User(customer.getUserName(), 
                customer.getPassword(), grantList);
 
        return userDetails;
    }

	@Override
	public CustomerDTO createCustomerAfteOAuthLoginSuccess(String name, String email, Provider provider) {
		
		Customer theCustomer = new Customer();
		
		theCustomer.setUserName(email);
		theCustomer.setFirstName(name);
		theCustomer.setEmail(email);
		theCustomer.setProvider(provider);
		theCustomer.setCreatedDate(new Date());
		theCustomer.setRole(roleDao.findRoleByCode("ROLE_CUSTOMER"));
		theCustomer.setPassword(passwordEncoder.encode("kjsd1@Kjajs"));
		theCustomer.setEnabled(1);
		
		theCustomer =  customerDao.save(theCustomer);
		
		CustomerDTO theCustomerDTO = mapper.map(theCustomer, CustomerDTO.class);
		
		theCustomerDTO.setId(theCustomerDTO.getId());
		theCustomerDTO.setEnabled(1);
		theCustomerDTO.setRole(roleDao.findRoleByCode("ROLE_CUSTOMER"));
		
		
		return theCustomerDTO;
		
		
	}

	@Override
	public Long count() {
		
		return customerDao.count();
	}

	@Override
	public Iterable<CustomerDTO> search(IPaging paging, String theSearchValue) {
		Iterable<Customer> theCustomers = customerDao.search(paging, theSearchValue);
		
		TypeToken<Iterable<CustomerDTO>> typeToken = new TypeToken<Iterable<CustomerDTO>>() {
		};

		Iterable<CustomerDTO> theCustomerDTOs = mapper.map(theCustomers, typeToken.getType());
		
		return theCustomerDTOs;
	}

	
}
