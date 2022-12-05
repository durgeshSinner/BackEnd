package com.ShoppingCart.App.Exception;

public class UserSecurityException extends Exception{
	public  UserSecurityException (String message) {
		super(message);
	}
	public  UserSecurityException (String message,Throwable cause) {
		super(message,cause);
	}

}
