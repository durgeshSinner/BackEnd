package com.ShoppingCart.App.Exception;

@SuppressWarnings("serial")
public class APIException extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public APIException(String message) {
		super();
		this.message = message;
	}

	
	

}
