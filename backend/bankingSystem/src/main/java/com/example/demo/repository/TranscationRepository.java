package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Transcation;

public interface TranscationRepository extends JpaRepository<Transcation, Long>{

}
