package com.ShoppingCart.App.Entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String Street;
	private String City;
	private String State;
	private String Pincode;
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPincode() {
		return Pincode;
	}
	public void setPincode(String pincode) {
		Pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [Street=" + Street + ", City=" + City + ", State=" + State + ", Pincode=" + Pincode + "]";
	}
	public Address(String street, String city, String state, String pincode) {
		super();
		Street = street;
		City = city;
		State = state;
		Pincode = pincode;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
