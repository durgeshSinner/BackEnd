package com.ShoppingCart.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.UserCredentials;

@Component
public interface UserCredRepository extends JpaRepository< UserCredentials , String>{

	
}
