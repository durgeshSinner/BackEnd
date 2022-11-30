package com.ShoppingCart.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Entities.CartItem;
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
	public Cart GetCartById(@PathVariable("userId") int userId) {
		return service.GetCart(userId);
		
	}
	@GetMapping("/{userId}/getCartItem/{cartitemId}")
	public CartItem GetCartItem(@PathVariable("userId") int userId, @PathVariable("cartitemId") int cartitemId) {
		return cartitemservice.getCartItem(userId, cartitemId);
	}
	
	@GetMapping("/{userId}/add/{productId}")
	public CartItem AddtoCart(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {
		CartItem item = cartitemservice.AddCartItem(userId, productId);
		
		return item;
	}
	@GetMapping("/{userId}/remove/{productId}")
	public String RemoveFromCart(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {
		return cartitemservice.RemoveItem(userId, productId);
	}
	@PostMapping("/{userId}/changequantity/{productId}")
	public CartItem ChangeQuantity(@PathVariable("userId") int userId, @PathVariable("productId") int productId,
			@RequestBody Quantity quantity) {
		
		return cartitemservice.ChangeProductQuantity(userId, productId, quantity.getQuantity());
	}
	@GetMapping("/clear/{userId}")
	public Cart clearCart(@PathVariable("userId") int userId) {
		return service.ClearCartItems(userId);
	}
	

}
