package com.ShoppingCart.App.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Repositories.CartItemRepository;
import com.ShoppingCart.App.Repositories.CartRepository;

@Component
public class Cartservice {
	@Autowired 
	private CartRepository cartrepository;
	@Autowired 
	private CartItemRepository cartitemrepository;

	public Cart GetCart(int userId) {
		return cartrepository.findById(userId).get();
	}
	
	public Cart CreateCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setCartId( user.getUserId());
		return cartrepository.save(cart);
	}
	@Transactional
	public void ClearCartItems(int UserId) {
		cartitemrepository.DeleteCartItemsByUserId(UserId);
		
	}
	
}
