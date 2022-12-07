package com.ShoppingCart.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.App.Entities.Voucher;
import com.ShoppingCart.App.Services.VoucherService;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
	@Autowired
	private VoucherService service;
	@GetMapping("/get/{userId}")
	public ResponseEntity<Voucher> getVoucher(@PathVariable("userId") int UserId) {
		try {
			return new ResponseEntity<Voucher>(service.getVoucher(UserId), HttpStatus.OK);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.getVoucher(UserId));
		}
	}
	@GetMapping("/create/{userId}/{offernumber}")
	public ResponseEntity<Voucher> createVoucher(@PathVariable("userId") int UserId,@PathVariable("offernumber") int offernumber) {
		try {
			return ResponseEntity.ok(service.createVoucher(UserId, offernumber));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.getVoucher(UserId));
		}
		
	}

}
