package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByUsernameAndLoginPasswd(String username, String password);

	Optional<Account> findByUsername(String username);

}
