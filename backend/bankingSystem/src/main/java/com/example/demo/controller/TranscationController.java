package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.TranscationDTO;
import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Account;
import com.example.demo.model.Payee;
import com.example.demo.model.Transcation;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TranscationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@RestController
@CrossOrigin("*")
public class TranscationController {

	@Autowired
	private TranscationRepository transcationRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/deposit")
	public ResponseEntity<?> addMoney(@RequestBody Transcation tr) {

		Optional<Account> currentAccount = accountRepository.findById(tr.getCreditAccNum());
		try {
			if(currentAccount.isPresent()) {
//				Date currentTimestamp = new Date(System.currentTimeMillis());
//	            tr.setTimestamp(currentTimestamp);
				transcationRepository.save(tr);
				currentAccount.get().setBalance(currentAccount.get().getBalance()+tr.getAmountTransfer());
				accountRepository.save(currentAccount.get());
				SuccessResponse<String> successResponse = new SuccessResponse<>("Transaction Completed!");
		        return ResponseEntity.ok(successResponse);
			}
			throw new UserNotFoundException("Account number is not exist");
		}
		catch (UserNotFoundException ex) {
			throw new InternalServerException("Account Number doesn't exist");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new InternalServerException("Transaction failed");
		}
		
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<?> transferMoney(@RequestBody Transcation tr) {
		Optional<Account> currentAccount = accountRepository.findById(tr.getAccount().getAccountNum());
		try {
			
			if (!currentAccount.isPresent()) {
	            throw new UserNotFoundException("Account number does not exist");
	        }
			if(tr.getAmountTransfer() <= currentAccount.get().getBalance()){	

//				Date currentTimestamp = new Date(System.currentTimeMillis());
//	            tr.setTimestamp(currentTimestamp);
				transcationRepository.save(tr);
				currentAccount.get().setBalance(currentAccount.get().getBalance() - tr.getAmountTransfer());
				accountRepository.save(currentAccount.get());
				
				// update the credit account,if it is in same bank
				Optional<Account> creditAccount = accountRepository.findById(tr.getCreditAccNum());
				if(creditAccount.isPresent()) {
					creditAccount.get().setBalance(creditAccount.get().getBalance() + tr.getAmountTransfer());
					accountRepository.save(creditAccount.get());
				}
				SuccessResponse<String> successResponse = new SuccessResponse<>("Transaction Completed!");
		        return ResponseEntity.ok(successResponse);
			}
			else {
	            throw new InsufficientBalanceException("Low Balance");
	        }
		}
		catch (InsufficientBalanceException ex) {
			throw new InternalServerException("Insufficient Balance");
	    }
		catch (UserNotFoundException ex) {
			throw new InternalServerException("Account Number doesn't exist");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new InternalServerException("Transaction failed");
		}
	}
	
	@GetMapping("/summary/{accountNum}")
	public ResponseEntity<?> getSummary(
			@PathVariable(name = "accountNum") long accountNum, 
			@RequestParam(name = "fromDate", required = false) Date fromDate, 
			@RequestParam(name = "toDate", required = false) Date toDate
			) {
		
	    Pageable numOfRecord = PageRequest.of(0, 5); // Page 0, 5 rows per page
	    
	    try {
	    	List<Transcation> transcations = new ArrayList<>();
	    	if(fromDate != null && toDate != null) {
				transcations = transcationRepository.findByCreditAccNumOrDebitAccNumAndTimestampBetween(accountNum, fromDate, toDate,numOfRecord);
				SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
		        return ResponseEntity.ok(successResponse);			
			}
			else if (fromDate != null && toDate == null) {
				transcations =  transcationRepository.findByCreditAccNumOrDebitAccNumdateAfterOrderByTimestampDesc(accountNum, fromDate,numOfRecord); 
				SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
				return ResponseEntity.ok(successResponse);
			} 
			else if (toDate != null && fromDate == null) {
				transcations =  transcationRepository.findByCreditAccNumOrDebitAccNumDateBeforeOrderByTimestampDesc(accountNum, toDate,numOfRecord); 
				SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
				return ResponseEntity.ok(successResponse);
			}
			else {
				transcations =  transcationRepository.findByCreditAccNumOrDebitAccNumOrderByTimestampDesc(accountNum,numOfRecord); 
				SuccessResponse<List<TranscationDTO> > successResponse = new SuccessResponse<>("Fetched transactions successfully", Mapper.transcationsEntitytoDTO(transcations));
				return ResponseEntity.ok(successResponse);
			}
	    }
	    catch (Exception ex) {
	    	System.out.println(ex.getMessage());
			throw new InternalServerException("No Transaction found");
	    	
	    }
		
		
	}
}
