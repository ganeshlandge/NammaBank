package com.example.demo.dto;

public class LoginResponse {
	private long userId;
	private long accountNum;
	private String username;
	private String email;
	public LoginResponse() {
		super();
	}
	public LoginResponse(long userId, long accountNum, String username, String email) {
		super();
		this.userId = userId;
		this.accountNum = accountNum;
		this.username = username;
		this.email = email;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
