package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ForgotUsername;

public interface ForgotUsernameRepository extends JpaRepository<ForgotUsername, Long>{

}
