package com.ShoppingCart.App.Services;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Exception.APIException;
import com.ShoppingCart.App.Exception.UserSecurityException;
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

	public User TokenMatcher(int Id, String tokenHeader) throws UserSecurityException, NoSuchElementException{
		String Token = tokenHeader.substring(7);
		String userEmail= jwtutil.extractUsername(Token);
		try {User user = userrepository.findById(Id).get();
		if(!user.getCredentials().getUserEmail().equals(userEmail)) 
		{throw new UserSecurityException("Can not do this action");}
		else {return user;}
		}
		catch(NoSuchElementException e) {
			throw new UserSecurityException("Can not do this action");
		}
		
		
	}
	
	public User GetUser(int Id, String tokenHeader) throws NoSuchElementException, UserSecurityException {
			return this.TokenMatcher(Id, tokenHeader);
		
	}
	public void UpdateUser(User user, String tokenHeader) throws NoSuchElementException, UserSecurityException {
		User checkedUser = this.TokenMatcher(user.getUserId(), tokenHeader);
		
		checkedUser.setUserName(user.getUserName());
		checkedUser.setUserAddress(user.getUserAddress());
		checkedUser.setUserPhone(user.getUserPhone());
			userrepository.save(checkedUser);
		
		
	}
	
	public User CreateUser(User user) throws APIException{
		
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
		
		return newuser;
		
	}
	
}
