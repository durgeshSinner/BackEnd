package com.ShoppingCart.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Services.CartItemService;
import com.ShoppingCart.App.Services.Cartservice;
import com.ShoppingCart.App.TokenHelper.Quantity;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private Cartservice service;
	@Autowired
	private CartItemService cartitemservice;

	@GetMapping("/{userId}/getCart")
	public ResponseEntity<Cart> GetCartById(@PathVariable("userId") int userId) {
		try {
			Cart cart = service.GetCart(userId);
			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{userId}/add/{productId}")
	public ResponseEntity<Void> AddtoCart(@PathVariable("userId") int userId,
			@PathVariable("productId") int productId) {
		try {
			cartitemservice.AddCartItem(userId, productId);
			return new ResponseEntity<Void>( HttpStatus.OK);
		} 
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{userId}/remove/{productId}")
	public ResponseEntity<Void> RemoveFromCart(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {
		try {cartitemservice.RemoveItem(userId, productId);
		  return new ResponseEntity<Void>( HttpStatus.OK); }
		catch(Exception e) {return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
		
	}

	@PostMapping("/{userId}/changequantity/{productId}")
	public ResponseEntity<Void> ChangeQuantity(@PathVariable("userId") int userId, @PathVariable("productId") int productId,
			@RequestBody Quantity quantity) {
		try {cartitemservice.ChangeProductQuantity(quantity.getQuantity(), userId, productId);
		  return new ResponseEntity<Void>( HttpStatus.OK); }
		catch(Exception e) {return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
	}


}
