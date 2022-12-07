package com.ShoppingCart.App.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.UserCredentials;

@Component
public interface UserCredRepository extends JpaRepository< UserCredentials , String>{
	@Query(value = "select user_email from user_credentials;", nativeQuery = true)
	List<String> getAllUserEmails();

	
}
