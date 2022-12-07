package com.ShoppingCart.App.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Repositories.UserCredRepository;
@Component
public class UsercredentialServices {
	@Autowired
	private UserCredRepository ucrepository;
	
	public List<String> getUserEmails() {
		return ucrepository.getAllUserEmails();
	}
	
	
}
