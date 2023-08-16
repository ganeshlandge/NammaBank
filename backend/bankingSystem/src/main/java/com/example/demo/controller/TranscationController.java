package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Transcation;
import com.example.demo.repository.TranscationRepository;

@RestController
public class TranscationController {

	@Autowired
	private TranscationRepository transcationRepository;
	
	@PostMapping("/transfer")
	public Transcation transferMoney(@RequestBody Transcation tr) {
		return transcationRepository.save(tr);
	}
}
