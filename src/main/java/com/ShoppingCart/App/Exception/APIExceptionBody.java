package com.ShoppingCart.App.Exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class APIExceptionBody {
	private final String message;
	private final Throwable throwable;
	private final HttpStatus status;
	private final ZonedDateTime time;
	public APIExceptionBody(String message, Throwable throwable, HttpStatus status, ZonedDateTime time) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.status = status;
		this.time = time;
	}
	@Override
	public String toString() {
		return "APIExceptionBody [message=" + message + ", throwable=" + throwable + ", status=" + status + ", time="
				+ time + "]";
	}
	public String getMessage() {
		return message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public ZonedDateTime getTime() {
		return time;
	}
}
