package com.nhuhoa.springboot.coffeestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhuhoa.springboot.coffeestore.entity.Role;

@Repository
public class RoleDao implements IRoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Iterable<Role> findAll() {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		Query<Role> theQuery = session.createQuery("from Role", Role.class);
		
		Iterable<Role> theResults = theQuery.getResultList();
		
		session.getTransaction().commit();
	    session.close();
		
		return theResults;
	}

	

	@Override
	public Role findRoleByCode(String code) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Role theRole = null;
		
		try {
			session.beginTransaction();
			
			Query<Role> theQuery = session.createQuery("from Role where code=:roleCode", Role.class);
			theQuery.setParameter("roleCode", code);
			
			theRole = theQuery.getSingleResult();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
		}
		
		return theRole;
	}



}
