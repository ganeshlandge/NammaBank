package com.example.demo.exception;

public class BadCredentialsException extends RuntimeException{
	public BadCredentialsException(String message) {
		super(message);
	}
}
