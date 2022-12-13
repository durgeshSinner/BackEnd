package com.ShoppingCart.App.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Repositories.UserCredRepository;

@Component
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserCredRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("verification !");
		UserCredentials uc =repository.findById(username).get();
		return new CustomUserDetail(uc);
	}

}
