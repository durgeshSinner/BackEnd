package com.ShoppingCart.App.Services;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Repositories.UserRepository;
import com.ShoppingCart.App.Services.Cartservice;
import com.ShoppingCart.App.TokenHelper.JWTUtil;

@Component
public class UserServices {
	
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private UsercredentialServices usercredservice;
	@Autowired
	private Cartservice cartservices;
	@Autowired
	private JWTUtil jwtutil;

	
	public User GetUser(int Id, String tokenHeader) throws NoSuchElementException, SecurityException {
			String Token = tokenHeader.substring(7);
			String userEmail= jwtutil.extractUsername(Token);
			User user = userrepository.findById(Id).get();
			
			if(!user.getCredentials().getUserEmail().equals(userEmail)) {
				
				throw new SecurityException("Can not do this action");
			}
			else {return user;}
		
	}
	public void UpdateUser(User user, String tokenHeader) throws NoSuchElementException, SecurityException {
		String Token = tokenHeader.substring(7);
		String userEmail= jwtutil.extractUsername(Token);
		User u = userrepository.findById(user.getUserId()).get();

		if(!u.getCredentials().getUserEmail().equals(userEmail)) {
			
			throw new SecurityException("Can not do this action");
		}
		else {
			u.setUserName(user.getUserName());
			u.setUserAddress(user.getUserAddress());
			u.setUserPhone(user.getUserPhone());
			userrepository.save(u);
		}
		
	}
	
	public User CreateUser(User user) throws Exception{
		
		UserCredentials newuc = new UserCredentials();
		newuc.setUserEmail(user.getCredentials().getUserEmail());
		newuc.setPassword(user.getCredentials().getPassword());
		newuc.setRole("ROLE_USER");
		UserCredentials Credentials = usercredservice.CreateUser(newuc);
		
		User newuser = new User();
		newuser.setCredentials(Credentials);
		newuser.setUserAddress(user.getUserAddress());
		newuser.setUserName(user.getUserName());
		newuser.setUserPhone(user.getUserPhone());
		int Id = userrepository.save(newuser).getUserId();
		newuser.setUserId(Id);
		cartservices.CreateCart(newuser);
		
		return userrepository.save(newuser);
		
	}
	public int GetUserbyUserEmail(String UserEmail) {
		User UserbyUserEmail = userrepository.findAll().stream().filter( user -> {
			if(user.getCredentials().getUserEmail().equals(UserEmail)) {return true;}
			else {return false;}
		}).findAny().get();
		return UserbyUserEmail.getUserId();
	}
}
