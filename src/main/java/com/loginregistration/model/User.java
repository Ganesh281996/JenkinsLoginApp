package com.loginregistration.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="User")
public class User implements Serializable
{
	private static final long serialVersionUID = 3447770717812313395L;

	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="UserID")
	private long id;
	
	@Column(name="FirstName",nullable=false)
	private String firstName;
	
	@Column(name="LastName",nullable=false)
	private String lastName;
	
	@Column(name="PhoneNumber",nullable=false,unique=true)
	private long phoneNumber;
	
	@Autowired(required=false)
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	
	@Column(name="Email",unique=true,nullable=false,updatable=false)
	private String email;
	
	@Column(name="Password",nullable=false)
	private String password;

	public User() {}
	
//	public User(String firstName, String lastName, long phoneNumber, Address address, String email, String password)
//	{
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.phoneNumber = phoneNumber;
////		this.address = address;
//		this.email = email;
//		this.password = password;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", email=" + email + ", password=" + password + "]";
	}
}