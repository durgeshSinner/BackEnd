package com.ShoppingCart.App.Exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

//@ControllerAdvice
public class ExceptionHandler {
	
//	@org.springframework.web.bind.annotation.ExceptionHandler(value= {APIException.class})
	public ResponseEntity<Object> APIExceptionHandler(APIException e){
		APIExceptionBody body =new APIExceptionBody(e.getMessage(),e,HttpStatus.BAD_REQUEST,ZonedDateTime.now());
		return new ResponseEntity(body,HttpStatus.BAD_REQUEST);

	}
}
