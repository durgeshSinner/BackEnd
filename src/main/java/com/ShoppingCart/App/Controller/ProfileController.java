package com.ShoppingCart.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Entities.UserCredentials;
import com.ShoppingCart.App.Repositories.UserCredRepository;
import com.ShoppingCart.App.Services.Cartservice;
import com.ShoppingCart.App.Services.UserServices;
import com.ShoppingCart.App.Services.UsercredentialServices;

@RestController
public class ProfileController {
	@Autowired
	private UserServices userservice;
	@Autowired
	private UsercredentialServices usercredservice;
	@Autowired
	private Cartservice cartservices;
	@Autowired
	private UserCredRepository usercredrepo;
//	@PostMapping("/login")
//	public String Login() {
//		return "<h1>log in user</h1>";
//	}
	
	@PostMapping("/signup")
	public ResponseEntity<Integer> Signup(@RequestBody User u ){
		try {
			User user = userservice.CreateUser(u);
			int i = user.getUserId();
			Cart cart = cartservices.CreateCart(user);
			System.out.println(cart);
			return new ResponseEntity<Integer>(i,HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	@GetMapping("/getusercred/{email}")
	public ResponseEntity<UserCredentials> GetUserCred(@PathVariable("email") String email){
		try {
			UserCredentials uc = usercredrepo.findById(email).get();
			
		    return  new ResponseEntity<UserCredentials>(uc , HttpStatus.OK);
			
		    
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	@GetMapping("/getprofile/{userId}")
	public ResponseEntity<User> GetUser(@PathVariable("userId") int userId) {
		try {
			User user = userservice.GetUser(userId);
			return  new ResponseEntity<User>(user , HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
//	@PostMapping("/logout")
//	public String Logout() {
//		return "<h1>log in user</h1>";
//	}
	@PostMapping("/updateprofile")
	public ResponseEntity<Void> Updateprofile(@RequestBody User user) {
		try {
			userservice.UpdateUser(user);
			return  new ResponseEntity<Void>( HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(401).build();
		}
		
	}
	@GetMapping("/getusers")
	public List<String> getUsers(){
		return usercredservice.getUserEmails();
	}
	

}
