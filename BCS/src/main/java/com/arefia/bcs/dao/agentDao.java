package com.arefia.bcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.bcs.entity.agentEntity;

public interface agentDao extends JpaRepository<agentEntity, Long> {
	@Query(value = "SELECT DISTINCT DATESTR FROM " + 
			"(SELECT CONCAT(CONCAT(YEAR(JOINEDDATE), '/'), " + 
			"CASE WHEN MONTH(JOINEDDATE) >= 10 THEN MONTH(JOINEDDATE) " + 
			"ELSE CONCAT('0', MONTH(JOINEDDATE)) END " + 
			")  DATESTR " + 
			"FROM AGENT_LIST " + 
			"WHERE ACCOUNTID IN (SELECT ACCOUNTID FROM AGENT_LIST WHERE REFERRERID = :ACCOUNTID) " + 
			"OR ACCOUNTID IN (SELECT ACCOUNTID FROM AGENT_LIST WHERE REFERRERID IN " + 
			"(SELECT ACCOUNTID FROM AGENT_LIST WHERE REFERRERID = :ACCOUNTID))) X", nativeQuery = true)
    public List<Object[]> getDateList(@Param("ACCOUNTID") String accountid);
    
    @Query(value="SELECT A.ACCOUNTID, B.USERNAME, C.IMTYPE, '1' AS CUSTLEVEL, COUNT(*) NUMS, COUNT(*) * 1000 AS BONUS " + 
    		"FROM AGENT_LIST A " + 
    		"JOIN USER_LIST B ON B.ACCOUNTID = A.ACCOUNTID " + 
    		"JOIN IMTYPE C ON C.IMUID = A.IMTYPE " + 
    		"WHERE A.REFERRERID = :ACCOUNTID AND A.JOINEDDATE BETWEEN :MONSTA AND :MONEND " + 
    		"GROUP BY A.ACCOUNTID, B.USERNAME, C.IMTYPE " + 
    		"UNION " + 
    		"SELECT X.ACCOUNTID, Y.USERNAME, Z.IMTYPE, '2' AS CUSTLEVEL, COUNT(*) NUMS, COUNT(*) * 500 AS BONUS " + 
    		"FROM AGENT_LIST X " + 
    		"JOIN USER_LIST Y ON Y.ACCOUNTID = X.ACCOUNTID " + 
    		"JOIN IMTYPE Z ON Z.IMUID = X.IMTYPE " + 
    		"WHERE X.REFERRERID IN (SELECT DISTINCT ACCOUNTID FROM AGENT_LIST WHERE REFERRERID = :ACCOUNTID) " + 
    		"AND X.JOINEDDATE BETWEEN :MONSTA AND :MONEND GROUP BY X.ACCOUNTID, Y.USERNAME, Z.IMTYPE", nativeQuery = true)
    public List<Object[]> calBonus(@Param("ACCOUNTID") String accountid, @Param("MONSTA") String monsta, @Param("MONEND") String monend);
    
    @Query(value = "SELECT * FROM AGENT_LIST WHERE ACCOUNTID = :ACCOUNTID", nativeQuery = true)
    public List<agentEntity> getPhoneList(@Param("ACCOUNTID") String accountid);
}
