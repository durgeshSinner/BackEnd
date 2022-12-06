package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Entities.CartItem;

@Component
public interface CartRepository extends JpaRepository<Cart, Integer> {
	

	
}
