package com.example.demo.dto;

public class NewPasswordDTO {
	private long accountNum;
	private String password;
	
	public NewPasswordDTO() {
		super();
	}
	public NewPasswordDTO(long accountNum, String password) {
		super();
		this.accountNum = accountNum;
		this.password = password;
	}
	public long getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
