package com.nhuhoa.springboot.coffeestore.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.entity.Product;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Repository
public class ProductDao implements IProductDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Iterable<Product> findAll(IPaging paging) {
		
		Session session = sessionFactory.getCurrentSession();
		Iterable<Product> theResults = null;
		
		try {
			session.beginTransaction();
			
			Query<Product> theQuery = session.createQuery("select p from Product p JOIN FETCH p.category", Product.class);
			
			if(paging.getLimit() != null) {
				theQuery.setFirstResult(paging.getOffset());
				theQuery.setMaxResults(paging.getLimit());
			} 
			
			
		    theResults = theQuery.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally {
			session.getTransaction().commit();
		    session.close();
		}
		
		return theResults;
	}

	@Override
	public Product findById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Product theProduct = null;
		
		try {
			session.beginTransaction();
			
			Query<Product> theQuery = session.createQuery("select p from Product p JOIN FETCH p.category where p.id=:id", Product.class);
			theQuery.setParameter("id", id);
			
			theProduct = (Product) theQuery.getSingleResult();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			
		    session.close();
		}
		return theProduct;
	}

	@Override
	public Product save(Product t) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		session.saveOrUpdate(t);
		
		session.getTransaction().commit();
	    session.close();
		
		return t;
	}

	@Override
	@Transactional
	public void remove(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			// the product is persistent in hibernate
			Product theProduct = session.get(Product.class, id);
			
			if(theProduct != null) {
				session.remove(theProduct);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} 
		finally {
			
			session.getTransaction().commit();
			session.close();
		}
	}
	
	@Override
	public Iterable<Product> findByCategoryId(Long theId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		Query<Product> theQuery = session.createQuery("from Product where category_id=:theId", Product.class);
		
		theQuery.setParameter("theId", theId);
		
		Iterable<Product> theResults = theQuery.getResultList();
		
		session.getTransaction().commit();
	    session.close();
		
		return theResults;
	}

	@Override
	public Long count() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query<Long> theQuery =session.createQuery("select count(*) from Product", Long.class);
		
		Long theTotalItem = (Long) theQuery.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return theTotalItem;
	}

	@Override
	public Iterable<Product> search(IPaging paging, String theSearchValue) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query<Product> theQuery = null;

		// only search by name if theSearchName is not empty

		if (theSearchValue != null && theSearchValue.trim().length() > 0) {
			// search for name and short description ... case insensitive
			theQuery = session.createQuery("from Product where lower(name) like :theName or lower(shortDescription) like :theName", Product.class);
			
			theQuery.setParameter("theName", "%" + theSearchValue.toLowerCase() + "%");
			
		} else {
			theQuery = session.createQuery("from Product", Product.class);
		}
		
		
		if(paging.getLimit() != null) {
			theQuery.setFirstResult(paging.getOffset());
			theQuery.setMaxResults(paging.getLimit());
		}
		

		// execute query and get result list
		Iterable<Product> customers = theQuery.getResultList();
		
		session.getTransaction().commit();
		session.close();

		return customers;
	}

	

}
