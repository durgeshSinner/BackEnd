package com.ShoppingCart.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
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
	private JWTUtil jwtutil;
	@Autowired
	private UserServices userservice;

	@PostMapping("/token")
	public ResponseEntity<?> TokenGenration(@RequestBody JwtRequest jwtrequest) {
		try {
			System.out.println("verification");
			this.authmanager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtrequest.getUserEmail(), jwtrequest.getPassword()));
			System.out.println("verification");
			UserDetails userdetails = customuserdetailservice.loadUserByUsername(jwtrequest.getUserEmail());
			String token = jwtutil.generateToken(userdetails);
			String role = userdetails.getAuthorities().toString();
			String Role = role.substring(6, role.length() - 1);
			int Id = userrepository.findByEmail(jwtrequest.getUserEmail()).getUserId();
			JwtResponse response = new JwtResponse(token, Id, Role);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/token/user")
	public ResponseEntity<Object[]> getuserCredentialsfromtoken(@RequestHeader("Authorization") String token) {
		try {
			String Token = token.substring(7);
			Object[] Result = userservice.CheckToken(Token);

			return new ResponseEntity<Object[]>(Result, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}
