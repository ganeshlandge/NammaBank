package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
class PayeeId implements Serializable {
    private Long account_num;
    private Long payee_acc_num;
	public PayeeId() {
		super();
	}
	public PayeeId(Long account_num, Long payee_acc_num) {
		super();
		this.account_num = account_num;
		this.payee_acc_num = payee_acc_num;
	}
	public Long getAcc_num() {
		return account_num;
	}
	public void setAcc_num(Long account_num) {
		this.account_num = account_num;
	}
	public Long getPayee_acc_num() {
		return payee_acc_num;
	}
	public void setPayee_acc_num(Long payee_acc_num) {
		this.payee_acc_num = payee_acc_num;
	}
}


@Entity
public class Payee {

    @EmbeddedId
    private PayeeId id;
    private String name;
	private String nick_name;

    @ManyToOne
    @MapsId("account_num")
    @JoinColumn(name = "account_num")
    @JsonBackReference
    private Account account;

	public Payee() {
		super();
	}

	public Payee(PayeeId id, String name, String nick_name, Account account) {
		super();
		this.id = id;
		this.name = name;
		this.nick_name = nick_name;
		this.account = account;
	}

	public PayeeId getId() {
		return id;
	}

	public void setId(PayeeId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
}
