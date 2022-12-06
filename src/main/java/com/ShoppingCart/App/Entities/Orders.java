package com.ShoppingCart.App.Entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	@JsonManagedReference
	@OneToMany( fetch=FetchType.EAGER, mappedBy="order",cascade = CascadeType.ALL)
	private List<OrderItem> OrderedProducts;
	private int userId;
	private String OrderedTime;
	@PrePersist
    public void prePersist() {
		LocalDateTime date= LocalDateTime.now();
		Integer day = date.getDayOfMonth();
		Integer Month = date.getMonthValue();
		Integer Year = date.getYear();
		this.OrderedTime = day.toString()+"/"+Month.toString()+"/" +Year.toString();
		
    }
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	public String getOrderedTime() {
		return OrderedTime;
	}
	public void setOrderedTime(String orderedTime) {
		OrderedTime = orderedTime;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", OrderedProducts=" + OrderedProducts + ", userId=" + userId
				+ ", OrderedTime=" + OrderedTime + "]";
	}
	public Orders(int orderId, List<OrderItem> orderedProducts, int userId, String orderedTime) {
		super();
		this.orderId = orderId;
		OrderedProducts = orderedProducts;
		this.userId = userId;
		OrderedTime = orderedTime;
	}
	public Orders() {
		super();
	}
	

}
