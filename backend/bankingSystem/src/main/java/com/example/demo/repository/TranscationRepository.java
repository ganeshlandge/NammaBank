package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Transcation;
import org.springframework.data.domain.Pageable;

public interface TranscationRepository extends JpaRepository<Transcation, Long>{
//	SELECT t.transcactionId, t.transcationType, t.amountTransfer, t.creditAccNum, t.account.accountNum as DebitAccNum, t.timestamp, t.remark
	@Query(" FROM Transcation t where t.creditAccNum = :accountNum or t.account.accountNum = :accountNum ORDER BY t.timestamp DESC")
	List<Transcation> findByCreditAccNumOrDebitAccNumOrderByTimestampDesc(@Param("accountNum") long accountNum, Pageable numOfRecord);
	
	@Query("FROM Transcation t WHERE (t.creditAccNum = :accountNum OR t.account.accountNum = :accountNum) AND t.timestamp BETWEEN :fromDate AND :toDate ORDER BY t.timestamp ASC")
    List<Transcation> findByCreditAccNumOrDebitAccNumAndTimestampBetween(@Param("accountNum") long accountNum, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable numOfRecord);

	@Query(" FROM Transcation t where (t.creditAccNum = :accountNum or t.account.accountNum = :accountNum) AND t.timestamp >= :fromDate ORDER BY t.timestamp DESC")
	List<Transcation> findByCreditAccNumOrDebitAccNumdateAfterOrderByTimestampDesc(@Param("accountNum") long accountNum,@Param("fromDate") Date fromDate, Pageable numOfRecord);

	@Query(" FROM Transcation t where (t.creditAccNum = :accountNum or t.account.accountNum = :accountNum) AND t.timestamp <= :toDate ORDER BY t.timestamp DESC")
	List<Transcation> findByCreditAccNumOrDebitAccNumDateBeforeOrderByTimestampDesc(@Param("accountNum") long accountNum,@Param("toDate") Date toDate, Pageable numOfRecord);

}
