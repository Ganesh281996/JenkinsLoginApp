package com.loginregistration.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.loginregistration.model.Address;
import com.loginregistration.model.User;

@Repository
public class RestDaoImpl implements RestDao 
{
	@Autowired
	@Qualifier("sessionFactory")
	public SessionFactory sessionFactory;
	
	Session session;
	
	public void insert(User user) 
	{
		System.out.println("Dao Insert Method");
		session=sessionFactory.getCurrentSession();
		session.save(user);
	}

	public boolean getUserByEmail(String email) 
	{
		System.out.println("Dao GetUserByEmail Method");
		session=sessionFactory.getCurrentSession();
		Query<User> query=session.createQuery("from User where Email=:email");
		query.setParameter("email", email);
		Iterator<User> iterator=query.list().iterator();
		return iterator.hasNext();
	}

	@Override
	public boolean checkAddress(Address address) 
	{
		System.out.println("Dao check address Method");
		session=sessionFactory.getCurrentSession();
		Query<Address> query=session.createQuery("from Address where Area=:area");
		query.setParameter("area", address.getArea());
		Iterator<Address> iterator=query.list().iterator();
		return iterator.hasNext();
	}

	@Override
	public User login(String email, String password) 
	{
		System.out.println("Dao Login Method");
		session=sessionFactory.getCurrentSession();
		Query<User> query=session.createQuery("from User where Email=:email and Password=:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		Iterator<User> iterator=query.list().iterator();
		if(iterator.hasNext())
		{
			System.out.println("User Found");
			return iterator.next();
		}
		else
		{
			System.out.println("User Not Found");
			return null;
		}
	}

	@Override
	public int delete(String email, String password) 
	{
		System.out.println("Dao Delete Method");
		session=sessionFactory.getCurrentSession();
		Query<User> query=session.createQuery("delete from User where Email=:email and password=:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		return query.executeUpdate();
	}

	@Override
	public List<User> display() 
	{
		session=sessionFactory.getCurrentSession();
		Query<User> query=session.createQuery("from User");
		return query.list();
	}
}