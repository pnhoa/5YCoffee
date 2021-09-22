package com.nhuhoa.springboot.coffeestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.Contact;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Repository
public class ContactDao implements IContactDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Iterable<Contact> findAll(IPaging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact save(Contact theContact) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		session.saveOrUpdate(theContact);
		
		session.getTransaction().commit();
	    session.close();
		
		return theContact;
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Contact> search(IPaging paging, String theSearchValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
