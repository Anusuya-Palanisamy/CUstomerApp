package com.aspire.CustomerApp.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Address {
	@NotEmpty(message = "City is compulsory")
	public String city;
	
	@NotEmpty(message = "State is compulsory")
	public String state;
	
	@NotEmpty(message = "Country is compulsory")
	public String country;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(String city, String state, String country) {
		super();
		this.city = city;
		this.state = state;
		
		this.country = country;
	}
	/*
	 * Implementation Getter and Setter
	 */
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
	

}
