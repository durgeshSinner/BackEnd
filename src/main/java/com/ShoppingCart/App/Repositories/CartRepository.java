package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Entities.CartItem;

@Component
public interface CartRepository extends JpaRepository< Cart,Integer>{
	@Modifying
	@Query(value ="update cart_item\n"
			+ "left join cart_products\n"
			+ "on cart_item.cart_item_id=cart_products.products_cart_item_id\n"
			+ "set cart_item.quantity=:q \n"
			+ "where cart_products.cart_cart_id=:u && cart_item.product_product_id=:p", nativeQuery=true)
	void updateQuantitybyUserIdandProductId(@Param("q") int quantity,@Param("u") int userId, @Param("p") int productId);
}
