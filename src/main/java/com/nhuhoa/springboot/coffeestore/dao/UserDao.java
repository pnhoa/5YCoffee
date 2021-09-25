package com.nhuhoa.springboot.coffeestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.User;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;

@Repository
public class UserDao implements IUserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Iterable<User> findAll(IPaging paging) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<User> theQuery = session.createQuery("SELECT u from User u JOIN FETCH u.roles where u.enabled=:enabled", User.class);
		theQuery.setParameter("enabled", 1);
		if(paging.getLimit() != null) {
			theQuery.setFirstResult(paging.getOffset());
			theQuery.setMaxResults(paging.getLimit());
		}
		
		
		
		Iterable<User> theUsers = theQuery.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return theUsers;
	}

	@Override
	public User findById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		
		User theUser = null;
		
		try {
			session.beginTransaction();
			
			Query<User> theQuery = session.createQuery("SELECT u from User u JOIN FETCH u.roles where u.id=:id AND u.enabled=:enabled", User.class);
			theQuery.setParameter("id", id);
			theQuery.setParameter("enabled", 1);
			
			theUser = theQuery.getSingleResult();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			theUser = null;
		} finally {
			session.close();
		}
		
		return theUser;
	}

	@Override
	public User save(User theUser) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		session.saveOrUpdate(theUser);
		
		session.getTransaction().commit();
		session.close();
		
		return theUser;
	}

	@Override
	public void remove(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User theUser = null;
		
		try {
			session.beginTransaction();
			
			theUser = session.get(User.class, id);
			
			theUser.setEnabled(0);
			
			session.saveOrUpdate(theUser);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			
			
			session.close();
		}

	}

	@Override
	public User findByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		
		User theUser = null;
		
		try {
			session.beginTransaction();
			
			Query<User> theQuery = session.createQuery("SELECT u from User u JOIN FETCH u.roles where username=:userName", User.class);
			theQuery.setParameter("userName", userName);
			
			theUser = theQuery.getSingleResult();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return theUser;
	}

	@Override
	public User findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();

		User theUser = null;
		
		try {
			session.beginTransaction();
			
			Query<User> theQuery = session.createQuery("SELECT u from User u JOIN FETCH u.roles where email=:email", User.class);
			theQuery.setParameter("email", email);
			
			theUser = theQuery.getSingleResult();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		return theUser;
	}

	@Override
	public Long count() {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query<Long> theQuery =session.createQuery("select count(*) from User where enabled=:enabled", Long.class);
		theQuery.setParameter("enabled", 1);
		
		Long theTotalItem = (Long) theQuery.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return theTotalItem;
	}

	

	@Override
	public Iterable<User> search(IPaging paging, String theSearchValue) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Query<User> theQuery;

		// only search by name if theSearchName is not empty

		if (theSearchValue != null && theSearchValue.trim().length() > 0) {
			// search for firstname or lastname ... case insensitive
			theQuery = session.createQuery(
					"SELECT distinct u from User u JOIN FETCH u.roles where u.enabled=:enabled AND (lower(u.firstName) like :theName or lower(u.lastName) like :theName"
					+ " or u.phone like :theName or lower(u.userName) like: theName or lower(u.email) like :theName)",
					User.class);
			
			theQuery.setParameter("theName", "%" + theSearchValue.toLowerCase() + "%");
			
		} else {
			theQuery = session.createQuery("SELECT distinct u from User u JOIN FETCH u.roles where u.enabled=:enabled", User.class);
		}
		
		theQuery.setParameter("enabled", 1);
		
		if(paging.getLimit() != null) {
			theQuery.setFirstResult(paging.getOffset());
			theQuery.setMaxResults(paging.getLimit());
		}
		

		// execute query and get result list
		Iterable<User> users = theQuery.getResultList();
		
		session.getTransaction().commit();
		session.close();

		return users;
	}

}
