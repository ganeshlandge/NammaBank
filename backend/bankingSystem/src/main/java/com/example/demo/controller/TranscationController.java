package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TranscationDTO;
import com.example.demo.mapper.Mapper;
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
	
	@GetMapping("/summary/{accountNum}")
	public List<TranscationDTO> getSummary(@PathVariable Long accountNum) {
		 List<Transcation> transcations =  transcationRepository.findByCreditAccNumOrDebitAccNum(accountNum); 
		 return Mapper.transcationsEntitytoDTO(transcations);
	}
}
