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
}
