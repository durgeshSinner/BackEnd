package com.ShoppingCart.App.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int OrderId;
	@OneToMany( fetch=FetchType.EAGER)
	private List<OrderItem> OrderedProducts;
	private int userId;
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public List<OrderItem> getOrderedProducts() {
		return OrderedProducts;
	}
	public void setOrderedProducts(List<OrderItem> orderedProducts) {
		OrderedProducts = orderedProducts;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Orders [OrderId=" + OrderId + ", OrderedProducts=" + OrderedProducts + ", userId=" + userId + "]";
	}
	public Orders(int orderId, List<OrderItem> orderedProducts, int userId) {
		super();
		OrderId = orderId;
		OrderedProducts = orderedProducts;
		this.userId = userId;
	}
	public Orders() {
		super();
	}
	

}
