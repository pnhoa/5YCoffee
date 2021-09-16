package com.nhuhoa.springboot.coffeestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.Category;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Repository
public class CategoryDao implements ICategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Iterable<Category> findAll(IPaging paging) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		Query<Category> theQuery = session.createQuery("from Category", Category.class);
		
		Iterable<Category> theResults = theQuery.getResultList();
		
		session.getTransaction().commit();
	    session.close();
		
		return theResults;
	}

	@Override
	public Category findById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Category theCategory = session.get(Category.class, id);
		
		session.getTransaction().commit();
	    session.close();
		
		return theCategory;
	}
	
	@Override
	public Category findOneByCode(String theCode) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		Query theQuery = session.createQuery("from Category where code=:theCode", Category.class);
		
		theQuery.setParameter("theCode", theCode);
		
		Category theResult = (Category) theQuery.getSingleResult();
		
		session.getTransaction().commit();
	    session.close();
		
		return theResult;
	}

	@Override
	public Category save(Category t) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(t);
		return t;
	}

	@Override
	public void remove(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = session.createNamedQuery("delte from Category where id=:theId");
		
		theQuery.setParameter("theId", id);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return 0L;
	}

	@Override
	public Iterable<Category> search(IPaging paging, String theSearchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
