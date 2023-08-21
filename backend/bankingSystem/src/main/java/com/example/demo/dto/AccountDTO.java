package com.example.demo.dto;

import javax.persistence.Column;

public class AccountDTO {
	private long accountNum;
	private String username;
	private String loginPasswd;
	private String transcationPasswd;
	private double balance;
	private String openDate;
	private String accountType;
	private int isDebitCard;
	private int isCreditCard;
	private int isNetBanking;
	private long timestamp;
	private int otp;
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
	public String getLoginPasswd() {
		return loginPasswd;
	}
	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}
	public String getTranscationPasswd() {
		return transcationPasswd;
	}
	public void setTranscationPasswd(String transcationPasswd) {
		this.transcationPasswd = transcationPasswd;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
}
