package com.loginregistration.service;

import java.util.List;

import com.loginregistration.model.User;

public interface RestService 
{
	public boolean getUserByEmail(String email);
	public boolean insert(User user);
	public User login(String email,String password);
	public User delete(String email,String password);
	public List<User> display();
}