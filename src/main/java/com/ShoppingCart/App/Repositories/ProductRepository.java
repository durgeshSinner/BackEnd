package com.ShoppingCart.App.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Products;
import com.ShoppingCart.App.Filter.FilterService;


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
}
