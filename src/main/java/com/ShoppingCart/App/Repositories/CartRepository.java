package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Cart;
@Component
public interface CartRepository extends JpaRepository<Cart, Integer> {
	

	
}
