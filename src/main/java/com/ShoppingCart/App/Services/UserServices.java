package com.ShoppingCart.App.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Address;
import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Repositories.UserRepository;

@Component
public class UserServices {
	
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private UsercredentialServices usercredservice;
	
	public User GetUser(int Id) {
		return (User)userrepository.findById(Id).get();
	}
	public void UpdateUser(User user) {
		
		User u = (User)userrepository.findById(user.getUserId()).get();
		u.setUserName(user.getUserName());
		u.setUserAddress(user.getUserAddress());
		u.setUserPhone(user.getUserPhone());
		userrepository.save(u);
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
