package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

		Iterable<Contact> theContacts = contactDao.findAll(paging); 
		
		TypeToken<Iterable<ContactDTO>> typeToken = new TypeToken<Iterable<ContactDTO>>() {
		};

		Iterable<ContactDTO> theContactDTOs = mapper.map(theContacts, typeToken.getType());
		
		return theContactDTOs;
	}

	@Override
	public ContactDTO findById(Long id) {
		
		Contact theContact = contactDao.findById(id);
		
		if(theContact == null) {
			return null;
		}
		
		ContactDTO theContactDTO = mapper.map(theContact, ContactDTO.class);
		
		return theContactDTO;
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
		
		contactDao.remove(id);
	}

	@Override
	public Iterable<ContactDTO> search(IPaging paging, String theSearchValue) {
		
		Iterable<Contact> theContacts = contactDao.search(paging, theSearchValue);
		
		TypeToken<Iterable<ContactDTO>> typeToken = new TypeToken<Iterable<ContactDTO>>() {
		};

		Iterable<ContactDTO> theContactDTOs = mapper.map(theContacts, typeToken.getType());
		
		return theContactDTOs;
	}

	@Override
	public Long count() {
		
		return contactDao.count();
	}

}
