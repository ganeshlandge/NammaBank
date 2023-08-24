package com.example.demo.service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.ForgotUsername;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Account> currentAccount = accountRepository.findByUsername(username);
		if(currentAccount.isPresent()) {
//			Account user = new Account();
//			user.setUsername(username);
//			user.setAccountNum(currentAccount.get().getAccountNum());
//			user.setEmail(currentAccount.get().getUser().getEmail());
//			user.setUserId(currentAccount.get().getUser().getId());
			return currentAccount.get();
		}
		throw new ResourceNotFoundException("User " + username + " does not exists");
		
	}

}
