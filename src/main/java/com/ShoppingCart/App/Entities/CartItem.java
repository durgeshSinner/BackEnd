package com.ShoppingCart.App.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CartItemId;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Cart cart;
	@OneToOne(fetch = FetchType.EAGER)
	private Products product;
	private int Quantity;
	public int getCartItemId() {
		return CartItemId;
	}
	public void setCartItemId(int cartItemId) {
		CartItemId = cartItemId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
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
	@Override
	public String toString() {
		return "CartItem [CartItemId=" + CartItemId + ", cart=" + cart + ", product=" + product + ", Quantity="
				+ Quantity + "]";
	}
	public CartItem(int cartItemId, Cart cart, Products product, int quantity) {
		super();
		CartItemId = cartItemId;
		this.cart = cart;
		this.product = product;
		Quantity = quantity;
	}
	public CartItem() {
		super();
	}
	
	
	

}
