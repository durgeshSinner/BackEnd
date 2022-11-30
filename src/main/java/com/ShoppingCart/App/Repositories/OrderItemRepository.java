package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.OrderItem;

@Component
public interface OrderItemRepository extends JpaRepository< OrderItem , Integer>{

}
