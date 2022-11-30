package com.ShoppingCart.App.TokenHelper;

public class JwtRequest {

	private String UserEmail;
	private String Password;
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public JwtRequest(String userEmail, String password) {
		super();
		UserEmail = userEmail;
		Password = password;
	}
	public JwtRequest() {
		super();
	}
	@Override
	public String toString() {
		return "JwtRequest [UserEmail=" + UserEmail + ", Password=" + Password + "]";
	}
	
}
