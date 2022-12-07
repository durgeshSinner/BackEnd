package com.ShoppingCart.App.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Exception.APIException;
import com.ShoppingCart.App.Exception.TokenSecurityException;
import com.ShoppingCart.App.Repositories.CartItemRepository;
import com.ShoppingCart.App.Repositories.CartRepository;
import com.ShoppingCart.App.TokenHelper.JWTUtil;

@Component
public class Cartservice {
	@Autowired
	private CartRepository cartrepository;
	@Autowired
	private CartItemRepository cartitemrepository;
	@Autowired
	private JWTUtil jwtutil;

	public Cart GetCart(int userId, String token) throws APIException {
		String Token = token.substring(7);
		String userEmail = jwtutil.extractUsername(Token);
		Cart usercart = cartrepository.findById(userId).get();
		if (usercart.getUser().getCredentials().getUserEmail().equals(userEmail)) {
			if (usercart.getProducts().size() == 0) {
				throw new APIException("No products in cart");
			} else {
				return usercart;
			}
		}
		else {throw new TokenSecurityException("token doesnot belong to the user profile requested");}

	}

	public Cart CreateCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setCartId(user.getUserId());
		return cartrepository.save(cart);
	}

	@Transactional
	public void ClearCartItems(int UserId) {
		cartitemrepository.DeleteCartItemsByUserId(UserId);

	}

}
