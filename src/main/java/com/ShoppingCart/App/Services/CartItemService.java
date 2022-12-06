package com.ShoppingCart.App.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ShoppingCart.App.Repositories.CartItemRepository;

@Component
public class CartItemService {
	@Autowired
	private CartItemRepository cartitemrepository;

	public int getQuantityof(int userId, int productId) {
		try {
			return cartitemrepository.findCartItemIdByUserIdandProductId(userId, productId);
		} catch (Exception e) {
			return 0;
		}

	}

	@Transactional
	public void AddCartItem(int userId, int productId) {
		int quantity = this.getQuantityof(userId, productId);
		if (quantity == 0) {
			cartitemrepository.addintoCartItem(userId, productId);
		} else {
			this.ChangeProductQuantity(quantity + 1, userId, productId);
		}
	}

	@Transactional
	public void RemoveItem(int userId, int productId) {
		System.out.println("method called");
		cartitemrepository.deleteCartItemByUserIdAndProductId(userId, productId);
	}

	@Transactional
	public void ChangeProductQuantity(int quantity, int userId, int productId) {

		cartitemrepository.updateQuantitybyUserIdandProductId(quantity, userId, productId);
	}
}
