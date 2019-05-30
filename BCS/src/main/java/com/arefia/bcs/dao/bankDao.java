package com.arefia.bcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.arefia.bcs.entity.bankEntity;

public interface bankDao extends JpaRepository<bankEntity, Long> {
	@Query(value = "SELECT * FROM BANK_RECORD WHERE ACCOUNTID = :ACCOUNTID", nativeQuery = true)
    public List<bankEntity> getBankList(@Param("ACCOUNTID") String accountid);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE BANK_RECORD SET ISDEFAULT = CASE WHEN BANKCODE = :BANKCODE AND BANKACCOUNT = :BANKACCOUNT THEN '1' " + 
            "ELSE '0' END WHERE ACCOUNTID = :ACCOUNTID", nativeQuery = true)
    public void updateDefault(@Param("ACCOUNTID") String accountid, @Param("BANKCODE") String bankcode, @Param("BANKACCOUNT") String bankaccount);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE BANK_RECORD SET ISENABLED = :ISENABLED WHERE ACCOUNTID = :ACCOUNTID AND BANKCODE = :BANKCODE AND BANKACCOUNT = :BANKACCOUNT", nativeQuery = true)
    public void updateenabled(@Param("ACCOUNTID") String accountid, @Param("BANKCODE") String bankcode, @Param("BANKACCOUNT") String bankaccount, @Param("ISENABLED") String isenabled);
}
