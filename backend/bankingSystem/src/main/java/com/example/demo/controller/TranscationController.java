package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.TranscationDTO;
import com.example.demo.exception.InternalServerException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Payee;
import com.example.demo.model.Transcation;
import com.example.demo.repository.TranscationRepository;

@RestController
@CrossOrigin("*")
public class TranscationController {

	@Autowired
	private TranscationRepository transcationRepository;
	
	@PostMapping("/transfer")
	public ResponseEntity<?> transferMoney(@RequestBody Transcation tr) {
		try {
			transcationRepository.save(tr);
			SuccessResponse<String> successResponse = new SuccessResponse<>("Transaction happened successfully");
	        return ResponseEntity.ok(successResponse);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new InternalServerException("Transaction failed");
		}
	}
	
	@GetMapping("/summary/{accountNum}")
	public ResponseEntity<?> getSummary(@PathVariable Long accountNum) {
			List<Transcation> transcations =  transcationRepository.findByCreditAccNumOrDebitAccNum(accountNum); 
			SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
	        return ResponseEntity.ok(successResponse);
	}
}
