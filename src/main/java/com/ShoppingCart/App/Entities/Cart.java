package com.ShoppingCart.App.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cart {
	@Id
	private int CartId;
	
	@OneToOne( fetch=FetchType.EAGER)
	private User user;
	
	@JsonManagedReference
	@OneToMany( fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="cart")
	private List<CartItem> products;

	public int getCartId() {
		return CartId;
	}

	public void setCartId(int cartId) {
		CartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getProducts() {
		return products;
	}

	public void setProducts(List<CartItem> products) {
		this.products = products;
	}

	public Cart(int cartId, User user, List<CartItem> products) {
		super();
		CartId = cartId;
		this.user = user;
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [CartId=" + CartId + ", user=" + user + ", products=" + products + "]";
	}

	public Cart() {
		super();
	}
	
	
	
	
	
}
