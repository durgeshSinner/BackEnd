package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.CartItem;

@Component
public interface CartItemRepository extends JpaRepository<CartItem , Integer>{
	@Modifying
	@Query(value= "INSERT INTO cart_item \n"
			+ "(cart_item_id, quantity, product_product_id )\n"
			+ "select (\n"
			+ "case when (\n"
			+ " select count(cart_item_id) \n"
			+ "from cart_item\n"
			+ "left join cart_products\n"
			+ "on cart_item_id= cart_products.products_cart_item_id\n"
			+ "where cart_cart_id=:u and product_product_id=:p \n"
			+ ") = 1 then (select cart_item_id \n"
			+ "from cart_item\n"
			+ "left join cart_products\n"
			+ "on cart_item_id= cart_products.products_cart_item_id\n"
			+ "where cart_cart_id=:u and product_product_id=:p \n"
			+ ")\n"
			+ "else null\n"
			+ "End\n"
			+ ")\n"
			+ " , 1, :p ;" , nativeQuery=true)
	int addintoCartItem(@Param("u") int userId, @Param("p") int productId);
	@Query(value="select LAST_INSERT_ID();", nativeQuery=true)
	int findlastinsertId();

}
