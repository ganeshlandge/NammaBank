package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.Login;
import com.example.demo.repository.AccountRepository;

@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/netbanking/login")
	public String login(@RequestBody Login login) {
		Optional<Account> account = accountRepository.findByUsernameAndLoginPasswd(login.getUsername(), login.getPassword());
		if(account.isPresent()) {
			return "Login successful";
		} else {
			return "Username or password is incorrect";
		}
	}
	
	@PostMapping("/netbanking/register")
	public String register(@RequestBody Account newAccount) {
		Account  currentAccount = accountRepository.findById(newAccount.getAccountNum())
				.orElseThrow(()->new UserNotFoundException("Account " + newAccount.getAccountNum() + " does not exists"));
		currentAccount.setUsername(newAccount.getUsername());
		currentAccount.setLoginPasswd(newAccount.getLoginPasswd());
		currentAccount.setTranscationPasswd(newAccount.getTranscationPasswd());
		accountRepository.save(currentAccount);
		return "Registration for Internet Banking is successful";
	}
	
	
}
