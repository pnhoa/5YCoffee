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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders findById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Orders theOrders = null;
		
		try {
			session.beginTransaction();
			
			Query<Orders> theQuery = session.createQuery("select o from Orders o JOIN FETCH o.customer JOIN FETCH o.user where o.id=:id", Orders.class);
			theQuery.setParameter("id", id);
			
			theOrders = (Orders) theQuery.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Orders> search(IPaging paging, String theSearchValue) {
		// TODO Auto-generated method stub
		return null;
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
