package com.ShoppingCart.App.Exception;

public class TokenSecurityException extends RuntimeException{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TokenSecurityException(String message) {
		super();
		this.message = message;
	}

	public TokenSecurityException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
