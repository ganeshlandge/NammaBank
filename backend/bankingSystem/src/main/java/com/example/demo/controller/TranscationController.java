package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.TranscationDTO;
import com.example.demo.exception.InternalServerException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Account;
import com.example.demo.model.Payee;
import com.example.demo.model.Transcation;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TranscationRepository;

@RestController
public class TranscationController {

	@Autowired
	private TranscationRepository transcationRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/deposit")
	public ResponseEntity<?> addMoney(@RequestBody Transcation tr) {

		Optional<Account> currentAccount = accountRepository.findById(tr.getCreditAccNum());
		try {
			currentAccount.get().setBalance(currentAccount.get().getBalance()+tr.getAmountTransfer());
//			transcationRepository.save(tr);
			SuccessResponse<String> successResponse = new SuccessResponse<>("Transaction Completed!");
	        return ResponseEntity.ok(successResponse);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new InternalServerException("Transaction failed");
		}
		
	}	
	@PostMapping("/transfer")
	public ResponseEntity<?> transferMoney(@RequestBody Transcation tr) {
//		System.out.println(tr.getAmountTransfer());
//		System.out.println("Account num:"+tr.getAccount().getAccountNum());
//		System.out.println("Account bal: "+ tr.getAccount().getBalance());
//		tr.getAccount().setBalance(5000);
//		System.out.println("Account bal: "+ tr.getAccount().getBalance());
		Optional<Account> currentAccount = accountRepository.findById(tr.getAccount().getAccountNum());
		
		try {
			if(tr.getAmountTransfer() <= currentAccount.get().getBalance()){
				transcationRepository.save(tr);
				currentAccount.get().setBalance(currentAccount.get().getBalance() - tr.getAmountTransfer());
				accountRepository.save(currentAccount.get());
				SuccessResponse<String> successResponse = new SuccessResponse<>("Transaction Completed!");
		        return ResponseEntity.ok(successResponse);
			}
			throw new InternalServerException("Insufficient Balance");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new InternalServerException("Transaction failed");
		}
	}
	
	@GetMapping("/summary/{accountNum}")
	public ResponseEntity<?> getSummary(@PathVariable(name = "accountNum") long accountNum, @RequestParam(name = "fromDate", required = false) Date fromDate, @RequestParam(name = "toDate", required = false) Date toDate) {
		
		if(fromDate != null && toDate != null) {
			List<Transcation> transcations = transcationRepository.findByCreditAccNumOrDebitAccNumAndTimestampBetween(accountNum, fromDate, toDate);
			SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
	        return ResponseEntity.ok(successResponse);			
		}
		else if (fromDate != null && toDate == null) {
			List<Transcation> transcations =  transcationRepository.findTop10ByCreditAccNumOrDebitAccNumdateAfterOrderByTimestampDesc(accountNum, fromDate); 
			SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
			return ResponseEntity.ok(successResponse);
		} 
		else if (toDate != null && fromDate == null) {
			List<Transcation> transcations =  transcationRepository.findTop10ByCreditAccNumOrDebitAccNumDateBeforeOrderByTimestampDesc(accountNum, toDate); 
			SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
			return ResponseEntity.ok(successResponse);
		}
		else {
			List<Transcation> transcations =  transcationRepository.findTop10ByCreditAccNumOrDebitAccNumOrderByTimestampDesc(accountNum); 
			SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
			return ResponseEntity.ok(successResponse);
		}
		
//		if(fromDate == null) {
//			fromDate = getFirstTransactiondate(accountNum);
//		}
//
//		if(toDate == null) {
//			toDate = getLastTransactionDate(accountNum);
//		}
//		Date fromDate = FromDate != null ? fromDate  : getFirstTransactiondate(accountNum);
//		Date toDate = ToDate() != null ? ToDate : getLastTransactionDate(accountNum);
//		System.out.println(fromDate);
//		System.out.println(toDate);
//		
//		List<Transcation> transcations = transcationRepository.findByCreditAccNumOrDebitAccNumAndTimestampBetween(accountNum,
//				fromDate, toDate);
//		
//		System.out.println("Prinitng--->");
//		System.out.println(transcations.size());
//		for(Transcation t: transcations) {
//			System.out.println(t);
//		}
//		
////			List<Transcation> transcations =  transcationRepository.findByCreditAccNumOrDebitAccNum(accountNum); 
//			SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
//			return ResponseEntity.ok(successResponse);
	}
//
//	private Date getFirstTransactiondate(long accountNum) {
//		Transcation firstTranscation = transcationRepository.findTopByCreditAccNumOrDebitAccNumOrderByTimestampAsc(accountNum);
//		System.out.println("printing:"+firstTranscation.getTimestamp());
//		Date currentDate = new Date(System.currentTimeMillis());
////		Date sqlDate = new Date();
//		return firstTranscation != null ? firstTranscation.getTimestamp() : currentDate ;
//	}
//
//	private Date getLastTransactionDate(long accountNum) {
//		Transcation lastTranscation = transcationRepository.findTopByCreditAccNumOrDebitAccNumOrderByTimestampDesc(accountNum);
//		Date currentDate = new Date(System.currentTimeMillis());
////		Date sqlDate = new Date(lastTranscation.getTimestamp());
//		return lastTranscation != null ? lastTranscation.getTimestamp() : currentDate ;
//	}
}
