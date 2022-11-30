package com.ShoppingCart.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ShoppingCart.App.Entities.Orders;
import com.ShoppingCart.App.Services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@GetMapping("/{userId}/getorders")
	public List<Orders> GetUserOrder(@PathVariable("userId") int userId) {
		return service.GetOrdersbyUserId(userId);
		}
		
	
	@GetMapping("/{userId}/createorder")
	public Orders CreateUserOrder(@PathVariable("userId") int userId) {
		return service.CreateOrders(userId);
	}

}
