package com.loginregistration.dao;

import java.util.List;

import com.loginregistration.model.Address;
import com.loginregistration.model.User;

public interface RestDao 
{
	public void insert(User user);
	public boolean getUserByEmail(String email);
	public boolean checkAddress(Address address);
	public User login(String email,String password);
	public int delete(String email,String password);
	public List<User> display();
}