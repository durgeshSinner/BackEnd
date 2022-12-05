package com.ShoppingCart.App.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CartItemId;
	@OneToOne(fetch = FetchType.EAGER)
	private Products product;
	private int Quantity;
	
	public CartItem() {
		super();
	}
	public CartItem(int cartItemId, Products product, int quantity) {
		super();
		CartItemId = cartItemId;
		this.product = product;
		Quantity = quantity;
	}
	public int getCartItemId() {
		return CartItemId;
	}
	public void setCartItemId(int cartItemId) {
		CartItemId = cartItemId;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	

}
