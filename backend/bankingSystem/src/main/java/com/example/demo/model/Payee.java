package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Payee {
	@Id
	private long acc_num;
	private long payee_acc_num;
	private String name;
	private String nick_name;
	
}
