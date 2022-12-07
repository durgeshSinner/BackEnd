package com.ShoppingCart.App.TokenHelper;

public class JwtResponse {
	private String token;
	private int Id;
	private String role;
	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", Id=" + Id + ", role=" + role + "]";
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponse(String token, int id, String role) {
		super();
		this.token = token;
		Id = id;
		this.role = role;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
