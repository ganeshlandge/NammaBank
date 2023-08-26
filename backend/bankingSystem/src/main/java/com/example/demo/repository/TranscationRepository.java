package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Transcation;


public interface TranscationRepository extends JpaRepository<Transcation, Long>{
//	SELECT t.transcactionId, t.transcationType, t.amountTransfer, t.creditAccNum, t.account.accountNum as DebitAccNum, t.timestamp, t.remark
	@Query(" FROM Transcation t where t.creditAccNum = :accountNum or t.account.accountNum = :accountNum ORDER BY t.timestamp DESC")
	List<Transcation> findTop10ByCreditAccNumOrDebitAccNumOrderByTimestampDesc(@Param("accountNum") long accountNum);
	
	
//	@Query("FROM Transcation t where t.creditAccNum = :accountNum OR t.account.accountNum = :accountNum ORDER BY t.timestamp ASC")
//	Transcation findTopByCreditAccNumOrDebitAccNumOrderByTimestampAsc(@Param("accountNum") long accountNum);
//
//	@Query("FROM Transcation t where t.creditAccNum = :accountNum OR t.account.accountNum = :accountNum ORDER BY t.timestamp DESC")
//	Transcation findTopByCreditAccNumOrDebitAccNumOrderByTimestampDesc(@Param("accountNum") long accountNum);

	@Query("FROM Transcation t WHERE t.creditAccNum = :accountNum OR t.account.accountNum = :accountNum AND t.timestamp BETWEEN :fromDate AND :toDate ORDER BY t.timestamp ASC")
	List<Transcation> findByCreditAccNumOrDebitAccNumAndTimestampBetween(@Param("accountNum") long accountNum,@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);


	@Query(" FROM Transcation t where t.creditAccNum = :accountNum or t.account.accountNum = :accountNum AND t.timestamp >= :fromDate ORDER BY t.timestamp DESC")
	List<Transcation> findTop10ByCreditAccNumOrDebitAccNumdateAfterOrderByTimestampDesc(@Param("accountNum") long accountNum,@Param("fromDate") Date fromDate);

	@Query(" FROM Transcation t where t.creditAccNum = :accountNum or t.account.accountNum = :accountNum AND t.timestamp <= :toDate ORDER BY t.timestamp DESC")
	List<Transcation> findTop10ByCreditAccNumOrDebitAccNumDateBeforeOrderByTimestampDesc(@Param("accountNum") long accountNum,@Param("toDate") Date toDate);




//	@Query("SELECT NEW com.example.demo.dto.TranscationSummaryDTO(t.transcactionId, t.transcationType, t.amountTransfer, t.creditAccNum, t.DebitAccNum, t.timestamp, t.remark) FROM Transcation t where t.creditAccNum = :accountNum or t.account.accountNum = :accountNum")
//	List<TranscationSummaryDTO> findTranscationSummaryDTOByCreditAccNumOrDebitAccNum(Long accountNum);


}
