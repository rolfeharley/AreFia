package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.arefia.lamm.entity.msgrecieveEntity;

public interface msgrecDao extends JpaRepository<msgrecieveEntity, Long> {
	@Query(value = "SELECT * FROM (SELECT RECIEVEID AS MSGID, SOURCERID, MSGTYPE, MSG, DURATION, RECIEVETIME AS EVENTTIME, 'RECIEVE' AS OBJECTTYPE, " + 
			       "CHECKFLAG FROM MSG_RECIEVE UNION SELECT PUSHID AS MSGID, SOURCERID, MSGTYPE, MSG, DURATION, PUSHTIME AS EVENTTIME, " + 
			       "'PUSH' AS OBJECTTYPE, CHECKFLAG FROM MSG_PUSH) X WHERE CHECKFLAG = 1 ORDER BY SOURCERID, EVENTTIME", nativeQuery = true)
    public List<Object[]> checkAllMessages();    

    @Transactional
    @Modifying
    @Query(value = "UPDATE MSG_RECIEVE SET CHECKFLAG = 1 WHERE RECIEVEID IN :RECIEVEID", nativeQuery = true)
    public void updateCheckFlag(@Param("RECIEVEID") List<String> RECIEVEID);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE MSG_RECIEVE SET CHECKFLAG = 1 WHERE MSGID = :MSGID", nativeQuery = true)
    public void updateSingleFlag(@Param("MSGID") String MSGID);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE MSG_RECIEVE SET CHECKFLAG = 2 WHERE SOURCERID = :SOURCERID", nativeQuery = true)
    public void moveRecieveToHistory(@Param("SOURCERID") String SOURCERID);
}