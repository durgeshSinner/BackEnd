package com.ShoppingCart.App.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int OrderItemId;
	@OneToOne(fetch = FetchType.EAGER)
	private Products product;
	private int Quantity;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	private Orders order;
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
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public OrderItem(int orderItemId, Products product, int quantity, Orders order) {
		super();
		OrderItemId = orderItemId;
		this.product = product;
		Quantity = quantity;
		this.order = order;
	}
	public OrderItem() {
		super();
	}
	@Override
	public String toString() {
		return "OrderItem [OrderItemId=" + OrderItemId + ", product=" + product + ", Quantity=" + Quantity + ", order="
				+ order + "]";
	}
	
}
