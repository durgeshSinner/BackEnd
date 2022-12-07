package com.ShoppingCart.App.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Repositories.UserRepository;
import com.ShoppingCart.App.TokenHelper.JWTUtil;

@Component
public class UserServices {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private Cartservice cartservices;
	@Autowired
	private JWTUtil jwtutil;
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;

	public User GetUser(int Id) {
		return userrepository.findById(Id).get();
	}

	public User UpdateUser(User user) {
		return userrepository.save(user);

	}

	public User CreateUser(User user){
		UserCredentials Credentials = user.getCredentials();
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
