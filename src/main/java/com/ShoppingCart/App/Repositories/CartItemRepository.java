package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.CartItem;

@Component
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	@Modifying
	@Query(value = "INSERT INTO cart_item (quantity,cart_cart_id, product_product_id )\n"
			+ "select 1, :u , :p ;", nativeQuery = true)
	void addintoCartItem(@Param("u") int userId, @Param("p") int productId);

	@Query(value = "select quantity \n"
			+ "from cart_item\n"
			+ "where cart_cart_id=:u and product_product_id=:p ;", nativeQuery = true)
	int findCartItemIdByUserIdandProductId(@Param("u") int userId, @Param("p") int productId);

	@Modifying
	@Query(value = "delete from cart_item\n"
			+ "where cart_cart_id=:u and product_product_id=:p ;", nativeQuery = true)
	void deleteCartItemByUserIdAndProductId(@Param("u") int userId, @Param("p") int productId);
	
	@Modifying
	@Query(value = "update cart_item\n"
			+ "set cart_item.quantity =:q \n"
			+ "where cart_cart_id=:u and product_product_id=:p ;", nativeQuery = true)
	void updateQuantitybyUserIdandProductId(@Param("q") int quantity, @Param("u") int userId,
			@Param("p") int productId);
	@Modifying
	@Query(value = "delete from cart_item\n"
			+ "where cart_cart_id=:u ;", nativeQuery = true)
	void DeleteCartItemsByUserId(@Param("u") int userId);
}
