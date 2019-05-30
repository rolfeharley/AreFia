package com.arefia.bcs.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.bcs.login.entity.loginEntity;

public interface loginDao extends JpaRepository<loginEntity, Long> {
	@Query(value = "SELECT A.ACCOUNTID AS ACCOUNTID, A.USERNAME AS USERNAME, A.PASSWD AS PASSWD, A.ISENABLE, " + 
			       "CASE WHEN B.ISADMIN IS NULL THEN '0' ELSE '1' END AS ISADMIN " + 
			       "FROM USER_LIST A " + 
			       "LEFT JOIN (SELECT ACCOUNTID, COUNT(*) AS ISADMIN FROM USER_IN_GROUP " + 
			       "WHERE GROUPID = 'sysadm' GROUP BY ACCOUNTID) B ON B.ACCOUNTID = A.ACCOUNTID " + 
			       "WHERE A.ACCOUNTID = :ACCOUNTID", nativeQuery = true)
	public loginEntity findLoginVerify(@Param("ACCOUNTID") String accountid);
}