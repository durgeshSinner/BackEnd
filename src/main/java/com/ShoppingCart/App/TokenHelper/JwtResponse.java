package com.ShoppingCart.App.TokenHelper;

public class JwtResponse {
	private String token;
	private int Id;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public JwtResponse(String token, int id) {
		super();
		this.token = token;
		Id = id;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
