package com.nhuhoa.springboot.coffeestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.Customer;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Repository
public class CustomerDao implements ICustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Iterable<Customer> findAll(IPaging paging) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Customer> theQuery = session.createQuery("SELECT c from Customer c JOIN FETCH c.role where c.enabled=:enabled", Customer.class);
		theQuery.setParameter("enabled", 1);
		if(paging.getLimit() != null) {
			theQuery.setFirstResult(paging.getOffset());
			theQuery.setMaxResults(paging.getLimit());
		}
		
		
		
		Iterable<Customer> theCustomers = theQuery.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return theCustomers;
	}

	@Override
	public Customer findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
		
		Customer theCustomer = null;
		
		try {
			session.beginTransaction();
			
			Query<Customer> theQuery = session.createQuery("SELECT c from Customer c JOIN FETCH c.role where c.id=:id AND c.enabled=:enabled", Customer.class);
			theQuery.setParameter("id", id);
			theQuery.setParameter("enabled", 1);
			
			theCustomer = theQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			theCustomer = null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return theCustomer;
	}

	@Override
	public void remove(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
		Customer theCustomer = null;
		
		try {
			session.beginTransaction();
			
			theCustomer = session.get(Customer.class, id);
			
			theCustomer.setEnabled(0);
			
			session.saveOrUpdate(theCustomer);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			
			session.getTransaction().commit();
			session.close();
		}

	}
	
	

	@Override
	public Customer findByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query<Customer> theQuery = session.createQuery("SELECT c from Customer c JOIN FETCH c.role where c.userName=:userName", Customer.class);
		theQuery.setParameter("userName", userName);
		
		Customer theCustomer = null;
		
		try {
			theCustomer = theQuery.getSingleResult();
		} catch (Exception e) {
			theCustomer = null;
		}
		
		session.getTransaction().commit();
		session.close();
		
		return theCustomer;
	}
	
	
	
	@Override
	public Customer findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query<Customer> theQuery = session.createQuery("SELECT c from Customer c JOIN FETCH c.role where c.email=:email", Customer.class);
		theQuery.setParameter("email", email);
		
		Customer theCustomer = null;
		
		try {
			theCustomer = theQuery.getSingleResult();
		} catch (Exception e) {
			theCustomer = null;
		}
		
		session.getTransaction().commit();
		session.close();
		
		return theCustomer;
	}


	@Override
	public Customer save(Customer theCustomer) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		session.saveOrUpdate(theCustomer);
		
		session.getTransaction().commit();
		session.close();
		
		return theCustomer;
	}

	@Override
	public Long count() {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query theQuery =session.createQuery("select count(*) from Customer where enabled=:enabled", Long.class);
		theQuery.setParameter("enabled", 1);
		
		Long theTotalItem = (Long) theQuery.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return theTotalItem;
	}

	@Override
	public Iterable<Customer> search(IPaging paging, String theSearchValue) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query<Customer> theQuery = null;

		// only search by name if theSearchName is not empty

		if (theSearchValue != null && theSearchValue.trim().length() > 0) {
			// search for firstname or lastname ... case insensitive
			theQuery = session.createQuery(
					"SELECT c from Customer c JOIN FETCH c.role where c.enabled=:enabled AND (lower(c.firstName) like :theName or lower(c.lastName) like :theName"
					+ " or c.phone like :theName or lower(c.userName) like: theName or lower(c.email) like :theName)",
					Customer.class);
			
			theQuery.setParameter("theName", "%" + theSearchValue.toLowerCase() + "%");
			
		} else {
			theQuery = session.createQuery("SELECT c from Customer c JOIN FETCH c.role where c.enabled=:enabled", Customer.class);
		}
		
		theQuery.setParameter("enabled", 1);
		
		if(paging.getLimit() != null) {
			theQuery.setFirstResult(paging.getOffset());
			theQuery.setMaxResults(paging.getLimit());
		}
		

		// execute query and get result list
		Iterable<Customer> customers = theQuery.getResultList();
		
		session.getTransaction().commit();
		session.close();

		return customers;
	}

	

	

}
