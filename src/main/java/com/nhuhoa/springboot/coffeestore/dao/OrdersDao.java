package com.nhuhoa.springboot.coffeestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.Orders;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Repository
public class OrdersDao implements IOrdersDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Iterable<Orders> findAll(IPaging paging) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		Query<Orders> theQuery = session.createQuery("select o from Orders o JOIN FETCH o.customer ORDER BY o.id DESC", Orders.class);
		if(paging.getLimit() != null) {
			theQuery.setFirstResult(paging.getOffset());
			theQuery.setMaxResults(paging.getLimit());
		}
		
		
		
		Iterable<Orders> theOrders = theQuery.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return theOrders;
	}

	@Override
	public Orders findById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Orders theOrders = null;
		
		try {
			session.beginTransaction();
			
			Query<Orders> theQuery = session.createQuery("select o from Orders o JOIN FETCH o.customer where o.id=:id", Orders.class);
			theQuery.setParameter("id", id);
			
			theOrders = (Orders) theQuery.getSingleResult();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			
		    session.close();
		}
		return theOrders;
	}

	@Override
	public Orders save(Orders theOrders) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		session.saveOrUpdate(theOrders);
		
		session.getTransaction().commit();
	    session.close();
		
		return theOrders;
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long count() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query<Long> theQuery =session.createQuery("select count(*) from Orders", Long.class);
		
		Long theTotalItem = (Long) theQuery.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return theTotalItem;
	}

	@Override
	public Iterable<Orders> search(IPaging paging, String theSearchValue) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query<Orders> theQuery = null;

		if (theSearchValue != null && theSearchValue.trim().length() > 0) {
			 theQuery = session.createQuery("select o from Orders o JOIN FETCH o.customer where o.id=:theId ORDER BY o.id DESC", Orders.class);
			 theQuery.setParameter("theId", Long.parseLong(theSearchValue));
		} else {
			session.getTransaction().commit();
			session.close();
			return null;
		}

		if(paging.getLimit() != null) {
			theQuery.setFirstResult(paging.getOffset());
			theQuery.setMaxResults(paging.getLimit());
		}
		
		Iterable<Orders> theOrders = theQuery.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return theOrders;
	}

	@Override
	public Iterable<Orders> findByCustomer(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		Query<Orders> theQuery = session.createQuery("select distinct o from Orders o JOIN FETCH o.ordersDetails WHERE customer_id=:theId", Orders.class);
		
		theQuery.setParameter("theId", id);
		
		Iterable<Orders> theResults = theQuery.getResultList();
		
		session.getTransaction().commit();
	    session.close();
		
		return theResults;
	}

}
