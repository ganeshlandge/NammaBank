package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.exception.InternalServerException;
import com.example.demo.model.Payee;
import com.example.demo.model.PayeeId;
import com.example.demo.repository.PayeeRepository;

@RestController
public class PayeeController {
	@Autowired
	private PayeeRepository payeeRepository;
	
	@PostMapping("/payee")
	public ResponseEntity<?> addPayee(@RequestBody Payee payee) {
		try {
			payeeRepository.save(payee);
			SuccessResponse<String> successResponse = new SuccessResponse<>("Added beneficiary successfully");
	        return ResponseEntity.ok(successResponse);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new InternalServerException("Adding beneficiary failed");
		}
	}
	
	@GetMapping("/payee/{accountNum}")
	public ResponseEntity<?> getPayee(@PathVariable Long accountNum) {
		List<Payee> payees =  payeeRepository.findByIdAccountNum(accountNum);
		SuccessResponse<List<Payee> > successResponse = new SuccessResponse<>("Fetched beneficiary successfully", payees);
        return ResponseEntity.ok(successResponse);
	}
}
