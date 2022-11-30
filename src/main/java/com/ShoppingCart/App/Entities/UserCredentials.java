package com.ShoppingCart.App.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCredentials {
	
	public UserCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserCredentials [UserEmail=" + UserEmail + ", password=" + password + ", Role=" + Role + "]";
	}
	public UserCredentials(String userEmail, String password, String role) {
		super();
		UserEmail = userEmail;
		this.password = password;
		Role = role;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	@Id
	private String UserEmail;
	private String password;
	private String Role;
	
}
