package com.loginregistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Address")
public class Address implements Serializable
{
	private static final long serialVersionUID = -1254840967016308113L;

	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="AddressID")
	private long id;
	
	@Column(name="Area",nullable=false,unique=true)
	private String area;
	
	@Column(name="City",nullable=false)
	private String city;
	
	@Column(name="State",nullable=false)
	private String state;
	
	@Column(name="Country",nullable=false)
	private String country;
	
	@Column(name="PinCode",nullable=false)
	private long pinCode;

	public Address() {}
	
	public Address(String area, String city, String state, String country, long pinCode) 
	{
		this.area = area;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
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
		Address other = (Address) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", area=" + area + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", pinCode=" + pinCode + "]";
	}
}