package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.dao.IContactDao;
import com.nhuhoa.springboot.coffeestore.dto.ContactDTO;
import com.nhuhoa.springboot.coffeestore.entity.Contact;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Service
@Transactional
public class ContactServiceIml implements ContactService {
	
	@Autowired
	private IContactDao contactDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Iterable<ContactDTO> findAll(IPaging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactDTO save(ContactDTO theContactDto) {
		
		Contact theContact = mapper.map(theContactDto, Contact.class);
		
		theContact.setCreatedBy(theContact.getName());
		theContact.setCreatedDate(new Date());
		
		theContact = contactDao.save(theContact);
		
		theContactDto.setId(theContact.getId());
		
		return theContactDto;
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<ContactDTO> search(IPaging paging, String theSearchValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
