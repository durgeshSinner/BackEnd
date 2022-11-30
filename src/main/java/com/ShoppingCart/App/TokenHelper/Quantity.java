package com.ShoppingCart.App.TokenHelper;

public class Quantity {

	private int Quantity;

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Quantity() {
		super();
	}

	public Quantity(int quantity) {
		super();
		Quantity = quantity;
	}

	@Override
	public String toString() {
		return "Quantity [Quantity=" + Quantity + "]";
	}
}
