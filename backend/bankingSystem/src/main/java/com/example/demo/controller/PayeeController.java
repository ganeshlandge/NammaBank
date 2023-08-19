package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Payee;
import com.example.demo.model.PayeeId;
import com.example.demo.repository.PayeeRepository;

@RestController
public class PayeeController {
	@Autowired
	private PayeeRepository payeeRepository;
	
	@PostMapping("/payee")
	public Payee addPayee(@RequestBody Payee payee) {
		return payeeRepository.save(payee);
	}
	
	@GetMapping("/payee/{accountNum}")
	public List<Payee> getPayee(@PathVariable Long accountNum) {
		return payeeRepository.findByIdAccountNum(accountNum);
	}
}
