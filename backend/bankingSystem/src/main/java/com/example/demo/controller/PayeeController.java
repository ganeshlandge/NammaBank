package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Payee;
import com.example.demo.repository.PayeeRepository;

@RestController
public class PayeeController {
	@Autowired
	private PayeeRepository payeeRepository;
	
	@PostMapping("/payee")
	public Payee addPayee(@RequestBody Payee payee) {
		return payeeRepository.save(payee);
	}
}
