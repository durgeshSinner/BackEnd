package com.ShoppingCart.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.App.SecurityConfig.CustomUserDetailService;
import com.ShoppingCart.App.Services.UserServices;
import com.ShoppingCart.App.TokenHelper.JWTUtil;
import com.ShoppingCart.App.TokenHelper.JwtRequest;
import com.ShoppingCart.App.TokenHelper.JwtResponse;

@RestController
public class TokenController {
	
	@Autowired
	private AuthenticationManager authmanager;
	@Autowired
	private CustomUserDetailService customuserdetailservice;
	@Autowired
	private UserServices userservice;
	
	@Autowired
	private JWTUtil jwtutil;

	@PostMapping("/token")
	public ResponseEntity<?> TokenGenration(@RequestBody JwtRequest jwtrequest) {
		this.authmanager
		.authenticate(new UsernamePasswordAuthenticationToken(jwtrequest.getUserEmail()
				,jwtrequest.getPassword()));
		UserDetails userdetails = customuserdetailservice.loadUserByUsername(jwtrequest.getUserEmail());
		String token = jwtutil.generateToken(userdetails);
		int Id = userservice.GetUserbyUserEmail(jwtrequest.getUserEmail());
		JwtResponse response = new JwtResponse(token, Id);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/test")
	public ResponseEntity<?> test(@RequestHeader("Authorization") String token){
		String username = null;
		if(token!=null && token.startsWith("Bearer ")) {
			String Token = token.substring(7);
			username= jwtutil.extractUsername(Token);
			System.out.println(username);
			
		}
		return ResponseEntity.ok(username);
	}

}
