package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ForgotPassword;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, String> {

}
