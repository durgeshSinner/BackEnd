package com.ShoppingCart.App.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Entities.OrderItem;
import com.ShoppingCart.App.Entities.Orders;
import com.ShoppingCart.App.Repositories.OrderItemRepository;
import com.ShoppingCart.App.Repositories.OrderRepository;

@Component
public class OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private Cartservice cartservice;
	@Autowired
	private UserServices userservice;
	@Autowired
	private OrderItemRepository orderitemrepository;
	
	public List<Orders> GetOrdersbyUserId(int userId){
		return repository.findAll().stream().filter(order -> {
			if(order.getUserId()==userId) {return true;}
			else {return false;}
		}).toList();
	}
	public Orders CreateOrders(int userId) {
		Cart usercart = cartservice.GetCart(userId);
		Orders userorder = new Orders();
		userorder.setUserId(userId);
		userorder.setOrderedProducts(usercart.getProducts().stream().map(cartitem -> {
			OrderItem orderitem = new OrderItem();
			orderitem.setOrderItemId(cartitem.getCartItemId());
			orderitem.setProduct(cartitem.getProduct());
			orderitem.setQuantity(cartitem.getQuantity());
			return orderitemrepository.save(orderitem);
		}).toList());
		cartservice.ClearCartItems(userId);
		return repository.save(userorder);
		
	}
	
}
