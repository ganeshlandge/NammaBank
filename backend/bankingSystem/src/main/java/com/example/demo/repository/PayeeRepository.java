package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Payee;

public interface PayeeRepository extends JpaRepository<Payee, Long>{

	List<Payee> findByIdAccountNum(Long accountNum);

}
