package com.ShoppingCart.App.Services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Exception.TokenSecurityException;
import com.ShoppingCart.App.Repositories.UserCredRepository;
import com.ShoppingCart.App.Repositories.UserRepository;
import com.ShoppingCart.App.TokenHelper.JWTUtil;

@Component
public class UserServices {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private UserCredRepository ucrepository;
	@Autowired
	private Cartservice cartservices;
	@Autowired
	private JWTUtil jwtutil;
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;

	public User GetUser(int Id, String token) throws TokenSecurityException {
		String Token = token.substring(7);
		String userEmail = jwtutil.extractUsername(Token);
		User user = userrepository.findById(Id).get();
		if (user.getCredentials().getUserEmail().equals(userEmail)) {
			return user;
		} else {
			throw new TokenSecurityException("token doesnot belong to the user profile requested");
		}
	}

	public User UpdateUser(User user, String token) {
		String Token = token.substring(7);
		String userEmail = jwtutil.extractUsername(Token);
		User currentuser = userrepository.findById(user.getUserId()).get();
		if (currentuser.getCredentials().getUserEmail().equals(userEmail)) {
			user.setCredentials(currentuser.getCredentials());
			return userrepository.save(user);
		} else {
			throw new TokenSecurityException("token doesnot belong to the user profile requested");
		}

	}

	public User CreateUser(User user) throws TokenSecurityException {
		UserCredentials Credentials = user.getCredentials();
		try {
			ucrepository.findById(Credentials.getUserEmail()).get();
			throw new TokenSecurityException("User Already Exists");
		} catch (NoSuchElementException e) {
			System.out.println("inside exception handler");
			String password = PasswordEncoder.encode(user.getCredentials().getPassword());
			Credentials.setRole("ROLE_USER");
			Credentials.setPassword(password);
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

	public Object[] CheckToken(String token) throws Exception {
		String username = jwtutil.extractUsername(token);
		User user = userrepository.findByEmail(username);
		Object[] userinfo = new Object[2];
		userinfo[0] = user.getUserId();
		String role = user.getCredentials().getRole();
		userinfo[1] = role.substring(5, user.getCredentials().getRole().length());
		return userinfo;

	}

}
