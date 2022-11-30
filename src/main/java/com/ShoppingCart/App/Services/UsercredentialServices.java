package com.ShoppingCart.App.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Repositories.UserCredRepository;
@Component
public class UsercredentialServices {
	@Autowired
	private UserCredRepository ucrepository;
	@Autowired 
	private BCryptPasswordEncoder PasswordEncoder; 
	
	public UserCredentials CreateUser (UserCredentials userCred) throws Exception{
		List<UserCredentials> checkUserCred=ucrepository.findAll().stream().filter(uc -> {
			if(uc.getUserEmail().equals(userCred.getUserEmail())) {
				return true;
			}
			else {return false;}
		}).toList();
		if(checkUserCred.isEmpty()) {
			String password = PasswordEncoder.encode(userCred.getPassword()); 
			userCred.setPassword(password);
			return  ucrepository.save(userCred);
		}
		else {
			throw new Exception("User Already Exists");
		}
		
	}
	public List<String> getUserEmails() {
		return ucrepository.findAll().stream()
				.map(usercred -> usercred.getUserEmail()).toList();
	}
	
	
}
