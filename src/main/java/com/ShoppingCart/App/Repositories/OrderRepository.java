package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Orders;

@Component
public interface OrderRepository extends JpaRepository< Orders, Integer> {

}
