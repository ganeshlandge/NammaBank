package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PayeeId implements Serializable {
    private Long accountNum;
    private Long payeeAccNum;
	public PayeeId() {
		super();
	}
	public PayeeId(Long accountNum, Long payeeAccNum) {
		super();
		this.accountNum = accountNum;
		this.payeeAccNum = payeeAccNum;
	}
	public Long getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(Long accountNum) {
		this.accountNum = accountNum;
	}
	public Long getPayeeAccNum() {
		return payeeAccNum;
	}
	public void setPayeeAccNum(Long payeeAccNum) {
		this.payeeAccNum = payeeAccNum;
	}
	
}

