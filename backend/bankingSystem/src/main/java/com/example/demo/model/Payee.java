package com.example.demo.model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Payee {

    @EmbeddedId
    private PayeeId id;
    private String name;
	private String nickName;

    @ManyToOne
    @MapsId("accountNum")
    @JoinColumn(name = "accountNum")
    @JsonBackReference
    private Account account;

	public Payee() {
		super();
	}

	public Payee(PayeeId id, String name, String nickName, Account account) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
