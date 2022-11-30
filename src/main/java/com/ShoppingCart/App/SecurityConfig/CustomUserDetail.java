package com.ShoppingCart.App.SecurityConfig;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ShoppingCart.App.Entities.UserCredentials;

public class CustomUserDetail implements UserDetails{
	private UserCredentials uc;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> set = new HashSet<>();
		set.add(new SimpleGrantedAuthority(this.uc.getRole()));
		return set;
	}

	public CustomUserDetail(UserCredentials uc) {
		super();
		this.uc = uc;
	}

	@Override
	public String getPassword() {
	
		return this.uc.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.uc.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
