package com.nhuhoa.springboot.coffeestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		
		Session session = sessionFactory.getCurrentSession();
		
		Iterable<Contact> theResults = null;
		
		try {
			session.beginTransaction();
			
			Query<Contact> theQuery = session.createQuery("from Contact ", Contact.class);
			
			if(paging.getLimit() != null) {
				theQuery.setFirstResult(paging.getOffset());
				theQuery.setMaxResults(paging.getLimit());
			} 
			
			
		    theResults = theQuery.getResultList();
		    
		    session.getTransaction().commit();
			
		} catch(Exception e){
			
			e.printStackTrace();
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
			
		}
		return theResults;
	}

	@Override
	public Contact findById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Contact theContact = null;
		
		try {
			session.beginTransaction();
			
			Query<Contact> theQuery = session.createQuery("select c from Contact c where c.id=:id", Contact.class);
			theQuery.setParameter("id", id);
			
			theContact = (Contact) theQuery.getSingleResult();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			
		    session.close();
		}
		return theContact;
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
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			// the contact is persistent in hibernate
			Contact theContact = session.get(Contact.class, id);
			
			if(theContact != null) {
				session.remove(theContact);
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} 
		finally {
			
			session.close();
		}
	}

	@Override
	public Long count() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query<Long> theQuery =session.createQuery("select count(*) from Contact", Long.class);
		
		Long theTotalItem = (Long) theQuery.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return theTotalItem;
	}

	@Override
	public Iterable<Contact> search(IPaging paging, String theSearchValue) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query<Contact> theQuery = null;

		// only search by name if theSearchName is not empty

		if (theSearchValue != null && theSearchValue.trim().length() > 0) {
			// search for name and subject ... case insensitive
			theQuery = session.createQuery("from Contact where lower(name) like :theName or lower(subject) like :theName", Contact.class);
			
			theQuery.setParameter("theName", "%" + theSearchValue.toLowerCase() + "%");
			
		} else {
			theQuery = session.createQuery("from Contact", Contact.class);
		}
		
		
		if(paging.getLimit() != null) {
			theQuery.setFirstResult(paging.getOffset());
			theQuery.setMaxResults(paging.getLimit());
		}
		

		// execute query and get result list
		Iterable<Contact> contacts = theQuery.getResultList();
		
		session.getTransaction().commit();
		session.close();

		return contacts;
	}

}
