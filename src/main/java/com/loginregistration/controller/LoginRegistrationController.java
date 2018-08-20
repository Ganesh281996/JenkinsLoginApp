package com.loginregistration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loginregistration.model.User;
import com.loginregistration.service.RestService;

@RestController
@RequestMapping("/ctrl")
public class LoginRegistrationController 
{
	@Autowired
	RestService restService;

	@PostMapping(value = "/register/")
	public ResponseEntity<String> register(@RequestBody User user) 
	{
		System.out.println("Controller Register Method");
		System.out.println("Data in request = " + user);
		System.out.println(restService.getUserByEmail(user.getEmail()));
		if(restService.getUserByEmail(user.getEmail())==false)
		{
			try
			{
				restService.insert(user);
				System.out.println("Insert Success");
				return new ResponseEntity<>("User is registered", HttpStatus.CREATED);
			} 
			catch (Exception e)
			{
				System.out.println("Insert Failed");
				return new ResponseEntity<>("Something went wrong",HttpStatus.CONFLICT);
			}
		} 
		else
		{
			return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping(value = "/login/")
	public ResponseEntity<User> login(String email, String password, HttpServletRequest request,HttpServletResponse response) 
	{
		HttpSession session = null;
		System.out.println("Controller Login Method");
		System.out.println("Data in request = " + email + "   " + password);
		User user = restService.login(email, password);
		if (user != null) 
		{
			try
			{
				session = request.getSession();
				session.setAttribute("user", user);
				return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
			}
			catch(Exception e)
			{
				System.out.println("Something went wrong");
				return new ResponseEntity<>(null,HttpStatus.CONFLICT);
			}
		} 
		else 
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "welcome")
	public ResponseEntity<String> welcome(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			return new ResponseEntity<>("Login First", HttpStatus.NOT_FOUND);
		} 
		else 
		{
			return new ResponseEntity<>(session.getAttribute("user").toString(), HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		if (session == null) 
		{
			return new ResponseEntity<>("Login First", HttpStatus.NOT_FOUND);
		} 
		else 
		{
			session.removeAttribute("user");
			session.invalidate();
			return new ResponseEntity<>("Successfully Logged Out", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/delete/",method=RequestMethod.POST)
	public ResponseEntity<User> deleteUser(@RequestParam String email, @RequestParam String password)
	{
		System.out.println("Delete Controller Method");
		System.out.println("Data in request = " + email + "   " + password);
		User user = restService.login(email, password);
		if (user != null)
		{
			try
			{
				restService.delete(email, password);
				System.out.println(user);
				return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
			}
			catch(Exception e)
			{
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} 
		else
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> display()
	{
		return restService.display();
	}

	@RequestMapping(value = "/test{id}", method = RequestMethod.GET)
	public void test(@PathVariable("id") String id)
	{
		System.out.println(id);
	}
}