package com.ShoppingCart.App.SecurityConfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ShoppingCart.App.TokenHelper.JWTUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private CustomUserDetailService customuserdetailservice;
	
	@Autowired
	private JWTUtil jwtutil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String HeaderToken = request.getHeader("Authorization");
		String username = null;
		String token = null;
		if(HeaderToken!=null && HeaderToken.startsWith("Bearer ")) {
			token = HeaderToken.substring(7);
			username= jwtutil.extractUsername(token);
			
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userdetails = customuserdetailservice.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationtoken =  new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
			usernamePasswordAuthenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationtoken);
			
		}
		filterChain.doFilter(request, response);
		}
		
		
	

}
