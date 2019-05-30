package com.arefia.bcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.bcs.entity.userEntity;

public interface userDao extends JpaRepository<userEntity, Long> {
	@Query(value = "SELECT A.* FROM USER_LIST A WHERE A.ACCOUNTID = :ACCOUNTID", nativeQuery = true)
    public userEntity findUserNameById(@Param("ACCOUNTID") String accountid);
	
	@Query(value = "SELECT A.* FROM USER_LIST A WHERE A.ISENABLE = 1 AND A.DISPLAYLIST = 1", nativeQuery = true)
    public List<userEntity> findEnabledUser();
    
    @Query(value = "SELECT A.ACCOUNTID, A.USERNAME, A.REFERRER, COUNT(B.ACCOUNTID) PHONECOUNT " + 
    		"FROM USER_LIST A " + 
    		"JOIN AGENT_LIST B ON B.ACCOUNTID = A.ACCOUNTID " + 
    		"WHERE A.ACCOUNTID LIKE %:KEYWORD% OR A.USERNAME LIKE %:KEYWORD% OR A.IDENTITY LIKE %:KEYWORD% " + 
    		"OR A.EMAIL LIKE %:KEYWORD% OR A.ADDRESS LIKE %:KEYWORD% OR A.REFERRER LIKE %:KEYWORD% OR A.REFERRERID LIKE %:KEYWORD% " + 
    		"OR B.MOBILE LIKE %:KEYWORD% " + 
    		"GROUP BY A.ACCOUNTID, A.USERNAME, A.REFERRER", nativeQuery = true)
    public List<Object[]> findUserByCondition(@Param("KEYWORD") String keyword);
    
	@Query(value = "SELECT REFERRER, REFERRERID FROM USER_LIST WHERE ACCOUNTID = :ACCOUNTID", nativeQuery = true)
	public List<Object[]> getreferrer(@Param("ACCOUNTID") String accountid);
}
