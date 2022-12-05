package com.ShoppingCart.App.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Products;


@Component
public interface ProductRepository extends JpaRepository< Products, Integer>{
	List<Products> findByProductCategory(String Category);
	
	@Query(value = "select * from products where (product_category=:c  && :m<product_price  && product_price<:M  );", nativeQuery = true)
	List<Products> findByCategoryFilters(@Param("c") String category,@Param("m") int minPrice,@Param("M") int maxPrice );
	
	@Query(value = "select * from products where (product_category=:c && product_sub_category=:s && :m<product_price  && product_price<:M  );", nativeQuery = true)
	List<Products> findBySubCategoryFilters(@Param("c") String category,@Param("s") String subCategory ,@Param("m") int minPrice,@Param("M") int maxPrice );
	
	@Query(value = "select * from products where (:m<product_price  && product_price<:M  );", nativeQuery = true)
	List<Products> findByPriceFilters(@Param("m") int minPrice,@Param("M") int maxPrice );
	
	@Query(value="select * from products where (product_name LIKE :s);", nativeQuery = true)
	List<Products> findByProductnameLike(@Param("s") String search);
	
	@Query(value = "select * from products where (product_category=:c && product_sub_category=:s  );", nativeQuery = true)
	List<Products> findBySubCategory(@Param("c") String category,@Param("s") String subCategory);
	
	@Modifying
	@Query(value = "update products\n"
			+ "set products.product_category=:c, products.product_details=:d, products.product_name=:n, products.product_price=:p,products.product_sub_category=:s, products.url=:u "
			+ "where products.product_id=:i ;", nativeQuery = true)
	void updateProducts(@Param("c") String category,@Param("s") String subCategory ,@Param("d") String details,@Param("n") String name ,@Param("i") int Id ,@Param("u") String url,@Param("p") int price);
}
