package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.TranscationDTO;
import com.example.demo.model.Transcation;

public class Mapper {

	public static List<TranscationDTO> transcationsEntitytoDTO(List<Transcation> transcations) {
		List<TranscationDTO> transcationDTOs = new  ArrayList<>();
		   for (Transcation transaction : transcations) {
			   TranscationDTO dto = new TranscationDTO();
	            dto.setAmountTransfer(transaction.getAmountTransfer());
	            dto.setCreditAccNum(transaction.getCreditAccNum());
	            dto.setDebitAccNum(transaction.getAccount().getAccountNum());
	            dto.setRemark(transaction.getRemark());
	            dto.setTimestamp(transaction.getTimestamp());
	            dto.setTranscactionId(transaction.getTranscactionId());
	            dto.setTranscationType(transaction.getTranscationType());
	            transcationDTOs.add(dto);
	        }
	        
	        return transcationDTOs;
	}
}
