package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Transcation;


public interface TranscationRepository extends JpaRepository<Transcation, Long>{
//	SELECT t.transcactionId, t.transcationType, t.amountTransfer, t.creditAccNum, t.account.accountNum as DebitAccNum, t.timestamp, t.remark
	@Query(" FROM Transcation t where t.creditAccNum = :accountNum or t.account.accountNum = :accountNum")
	List<Transcation> findByCreditAccNumOrDebitAccNum(@Param("accountNum") long accountNum);

//	@Query("SELECT NEW com.example.demo.dto.TranscationSummaryDTO(t.transcactionId, t.transcationType, t.amountTransfer, t.creditAccNum, t.DebitAccNum, t.timestamp, t.remark) FROM Transcation t where t.creditAccNum = :accountNum or t.account.accountNum = :accountNum")
//	List<TranscationSummaryDTO> findTranscationSummaryDTOByCreditAccNumOrDebitAccNum(Long accountNum);


}
