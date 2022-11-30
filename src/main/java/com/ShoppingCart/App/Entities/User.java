package com.ShoppingCart.App.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int UserId;
	private String UserName;
	@OneToOne
	private UserCredentials Credentials;
	private String UserPhone;
	private Address UserAddress;
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public UserCredentials getCredentials() {
		return Credentials;
	}
	public void setCredentials(UserCredentials credentials) {
		Credentials = credentials;
	}
	public String getUserPhone() {
		return UserPhone;
	}
	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	public Address getUserAddress() {
		return UserAddress;
	}
	public void setUserAddress(Address userAddress) {
		UserAddress = userAddress;
	}
	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", UserName=" + UserName + ", Credentials=" + Credentials + ", UserPhone="
				+ UserPhone + ", UserAddress=" + UserAddress + "]";
	}
	public User(int userId, String userName, UserCredentials credentials, String userPhone, Address userAddress) {
		super();
		UserId = userId;
		UserName = userName;
		Credentials = credentials;
		UserPhone = userPhone;
		UserAddress = userAddress;
	}
	public User() {
		super();
	}
	

}
