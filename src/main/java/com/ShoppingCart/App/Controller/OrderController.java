package com.ShoppingCart.App.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ShoppingCart.App.Entities.Orders;
import com.ShoppingCart.App.Exception.APIException;
import com.ShoppingCart.App.Exception.TokenSecurityException;
import com.ShoppingCart.App.Services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService service;

	@GetMapping("/{userId}/getorders")
	public ResponseEntity<List<Orders>> GetUserOrder(@PathVariable("userId") int userId) {
		try {
			List<Orders> orderslist = service.GetOrdersbyUserId(userId);
			return new ResponseEntity<List<Orders>>(orderslist, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{userId}/createorder")
	public ResponseEntity<Orders> CreateUserOrder(@PathVariable("userId") int userId,
			@RequestHeader("Authorization")  String HeaderToken) {
		try {
			Orders order = service.CreateOrders(userId, HeaderToken);
			return new ResponseEntity<Orders>(order, HttpStatus.OK);
		} catch (APIException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (TokenSecurityException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
