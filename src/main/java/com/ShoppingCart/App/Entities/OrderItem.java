package com.ShoppingCart.App.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int OrderItemId;
	@OneToOne(fetch = FetchType.EAGER)
	private Products product;
	private int Quantity;
	
	public OrderItem() {
		super();
	}
	public OrderItem(int orderItemId, Products product, int quantity) {
		super();
		OrderItemId = orderItemId;
		this.product = product;
		Quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItem [OrderItemId=" + OrderItemId + ", product=" + product + ", Quantity=" + Quantity + "]";
	}
	public int getOrderItemId() {
		return OrderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		OrderItemId = orderItemId;
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
