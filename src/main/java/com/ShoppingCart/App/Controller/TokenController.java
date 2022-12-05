package com.ShoppingCart.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Repositories.UserCredRepository;
import com.ShoppingCart.App.Repositories.UserRepository;
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
	private UserRepository userrepository;
	@Autowired
	private UserCredRepository usercredrepository;
	@Autowired
	private JWTUtil jwtutil;

	@PostMapping("/token")
	public ResponseEntity<?> TokenGenration(@RequestBody JwtRequest jwtrequest) {
		this.authmanager
		.authenticate(new UsernamePasswordAuthenticationToken(jwtrequest.getUserEmail()
				,jwtrequest.getPassword()));
		UserDetails userdetails = customuserdetailservice.loadUserByUsername(jwtrequest.getUserEmail());
		String role = userdetails.getAuthorities().toString();
		String Role = role.substring(6, role.length()-1);
		String token = jwtutil.generateToken(userdetails);
		int Id = userrepository.findByEmail(jwtrequest.getUserEmail()).getUserId();
		JwtResponse response = new JwtResponse(token, Id,Role);
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
	@GetMapping("/token/user")
	public ResponseEntity<Object[]> getuserCredentialsfromtoken(@RequestHeader("Authorization") String token){
		try {String username = null;
		String Token = token.substring(7);
		username= jwtutil.extractUsername(Token);
		User user = userrepository.findByEmail(username);
		Object[] userinfo = new Object[2]; 
		userinfo[0] = user.getUserId();
		String role = user.getCredentials().getRole();
		userinfo[1] = role.substring(5, user.getCredentials().getRole().length());
		return new ResponseEntity<Object[]>(userinfo, HttpStatus.OK);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build();
		}
		
	}

}
