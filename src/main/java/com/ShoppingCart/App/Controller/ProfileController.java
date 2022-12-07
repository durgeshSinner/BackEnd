package com.ShoppingCart.App.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.App.Entities.User;
import com.ShoppingCart.App.Services.UserServices;
import com.ShoppingCart.App.Services.UsercredentialServices;

@RestController
public class ProfileController {
	@Autowired
	private UserServices userservice;
	@Autowired
	private UsercredentialServices usercredservice;

	@PostMapping("/signup")
	public ResponseEntity<Integer> Signup(@RequestBody User u) {
		try {
			User user = userservice.CreateUser(u);
			int i = user.getUserId();
			return new ResponseEntity<Integer>(i, HttpStatus.OK);
		}  catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
//	@GetMapping("/getusercred/{email}")
//	public ResponseEntity<UserCredentials> GetUserCred(@PathVariable("email") String email){
//		try {
//			UserCredentials uc = usercredrepo.findById(email).get();
//			
//		    return  new ResponseEntity<UserCredentials>(uc , HttpStatus.OK);
//			
//		    
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//	}

	@GetMapping("/getprofile/{userId}")
	public ResponseEntity<User> GetUser(@PathVariable("userId") int userId) {
		try {
			User user = userservice.GetUser(userId);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/updateprofile")
	public ResponseEntity<User> Updateprofile(@RequestBody User user,
			@RequestHeader("Authorization") String tokenHeader) {
		try {
			User updateduser = userservice.UpdateUser(user);
			return new ResponseEntity<User>(updateduser, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getusers")
	public ResponseEntity<List<String>> getUsers() {
		try {
			return new ResponseEntity<List<String>>(usercredservice.getUserEmails(), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
