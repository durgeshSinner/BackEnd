package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.User;

@Component
public interface UserRepository extends JpaRepository< User, Integer> {
	
	@Query( value = "select * from user where credentials_user_email= :u ;", nativeQuery=true)
	User findByEmail(@Param("u") String userEmail);
}
