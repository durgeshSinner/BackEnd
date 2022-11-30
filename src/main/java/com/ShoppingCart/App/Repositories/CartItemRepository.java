package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.CartItem;

@Component
public interface CartItemRepository extends JpaRepository<CartItem , Integer>{

}