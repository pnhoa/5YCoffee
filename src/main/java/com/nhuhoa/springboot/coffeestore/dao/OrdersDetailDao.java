package com.nhuhoa.springboot.coffeestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.OrdersDetail;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Repository
public class OrdersDetailDao implements IOrdersDetailDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Iterable<OrdersDetail> findAll(IPaging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersDetail findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersDetail save(OrdersDetail theOrdersDetail) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		session.saveOrUpdate(theOrdersDetail);
		
		session.getTransaction().commit();
	    session.close();
		
		return theOrdersDetail;
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
	public Iterable<OrdersDetail> search(IPaging paging, String theSearchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdersDetail> findByOrderId(Long theId) {

		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		Query<OrdersDetail> theQuery = session.createQuery("select o from OrdersDetail o JOIN FETCH o.product where orders_id=:theId", OrdersDetail.class);
		
		theQuery.setParameter("theId", theId);
		
		List<OrdersDetail> theResults = theQuery.getResultList();
		
		session.getTransaction().commit();
	    session.close();
		
		return theResults;
	}

}
