package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.arefia.lamm.entity.followersEntity;

public interface followersDao extends JpaRepository<followersEntity, Long> {
	@Query(value = "SELECT A.USERID, A.DISPLAYNAME, A.PICTUREURL, A.STATUSMESSAGE, B.MSGFROM, B.MSGTYPE, " + 
			"CASE WHEN B.MSGTYPE = 'text' THEN CASE WHEN LENGTH(B.MSG) > 12 THEN " + 
			"CONCAT(SUBSTRING(B.MSG, 1, 12), '...') ELSE B.MSG END ELSE B.MSG END MSG, " + 
			"CASE WHEN C.UNREADCOUNT IS NULL THEN 0 ELSE C.UNREADCOUNT END UNREADCOUNT, " + 
			"CAST(B.EXETIME AS CHAR(100)) AS EXETIME FROM LINE_FOLLOWERS A JOIN " + 
			"(SELECT TA.SOURCERID, TA.MSGTYPE, TA.MSG, TA.MSGFROM, TA.EXETIME FROM " + 
			"(SELECT CHECKFLAG, SOURCERID, MSGTYPE, MSG, PUSHTIME AS EXETIME, 'PUSH' AS MSGFROM " + 
			"FROM MSG_PUSH UNION " + 
			"SELECT CHECKFLAG, SOURCERID, MSGTYPE, MSG,  RECIEVETIME AS EXETIME, 'RECIEVE' AS MSGFROM " + 
			"FROM MSG_RECIEVE) TA JOIN (SELECT SOURCERID, MAX(MAXTIME) AS MAXTIME FROM " + 
			"(SELECT SOURCERID, MAX(PUSHTIME) AS MAXTIME FROM MSG_PUSH " + 
			"WHERE CHECKFLAG <> 2 GROUP BY SOURCERID UNION SELECT SOURCERID, MAX(RECIEVETIME) AS MAXTIME " + 
			"FROM MSG_RECIEVE WHERE CHECKFLAG <> 2 GROUP BY SOURCERID) TTB " + 
			"GROUP BY SOURCERID) TB ON TB.SOURCERID = TA.SOURCERID AND TB.MAXTIME = TA.EXETIME " + 
			"WHERE TA.CHECKFLAG <> 2) B ON B.SOURCERID = A.USERID LEFT JOIN (SELECT SOURCERID, " + 
			"COUNT(*) AS UNREADCOUNT FROM MSG_RECIEVE WHERE CHECKFLAG = 0 GROUP BY SOURCERID) C " + 
			"ON C.SOURCERID = A.USERID", nativeQuery = true)
    public List<Object[]> getActiveFollowers();
    
    @Query(value = "SELECT MSGFROM, MSGTYPE, CASE WHEN MSGTYPE = 'text' THEN REPLACE(MSG, '\n', '<br/>') ELSE MSG END MSG, " + 
    		"CAST(EXETIME AS CHAR(100)) AS EXETIME, MSGID FROM (SELECT SOURCERID, MSGTYPE, MSG, PUSHTIME AS EXETIME, 'PUSH' AS MSGFROM, " + 
    		"MSGID, CHECKFLAG FROM MSG_PUSH UNION SELECT SOURCERID, MSGTYPE, MSG, RECIEVETIME AS EXETIME, " + 
    		"'RECIEVE' AS MSGFROM, MSGID, CHECKFLAG FROM MSG_RECIEVE) A  WHERE A.SOURCERID = :SOURCERID AND A.CHECKFLAG <> '2' ORDER BY EXETIME", nativeQuery = true)
    public List<Object[]> getSingleFollowerMsg(@Param("SOURCERID") String SOURCERID);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE MSG_RECIEVE SET CHECKFLAG = 1 WHERE SOURCERID = :SOURCERID AND CHECKFLAG <> '2'", nativeQuery = true)
    public void updateRecieveAllFlag(@Param("SOURCERID") String SOURCERID);
    
    @Query(value = "SELECT COUNT(*) ACCCNT FROM LINE_FOLLOWERS WHERE USERID = :SOURCERID", nativeQuery = true)
    public List<Object[]> checkFollowerIsExist(@Param("SOURCERID") String SOURCERID);
    
    @Query(value = "SELECT A.USERID, A.DISPLAYNAME FROM LINE_FOLLOWERS A JOIN " + 
    		"(SELECT TA.SOURCERID FROM (SELECT CHECKFLAG, SOURCERID FROM MSG_PUSH UNION " + 
    		"SELECT CHECKFLAG, SOURCERID FROM MSG_RECIEVE) TA WHERE TA.CHECKFLAG = 2) B ON B.SOURCERID = A.USERID", nativeQuery = true)
    public List<Object[]> getHistoryFollowers();
    
    @Query(value = "SELECT MSGFROM, MSGTYPE, CASE WHEN MSGTYPE = 'text' THEN REPLACE(MSG, '\\n', '<br/>') ELSE MSG END MSG, " + 
    		"CAST(EXETIME AS CHAR(100)) AS EXETIME, MSGID FROM " + 
    		"(SELECT SOURCERID, MSGTYPE, MSG, PUSHTIME AS EXETIME, 'PUSH' AS MSGFROM, MSGID, CHECKFLAG FROM MSG_PUSH " + 
    		"UNION SELECT SOURCERID, MSGTYPE, MSG, RECIEVETIME AS EXETIME, " + 
    		"'RECIEVE' AS MSGFROM, MSGID, CHECKFLAG FROM MSG_RECIEVE) A " + 
    		"WHERE A.SOURCERID = :SOURCERID AND A.CHECKFLAG = '2' AND A.EXETIME BETWEEN :STDATE AND :EDDATE ORDER BY EXETIME", nativeQuery = true)
    public List<Object[]> getSingleFollowerHistory(@Param("SOURCERID") String SOURCERID, @Param("STDATE") String STDATE, @Param("EDDATE") String EDDATE);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE LINE_FOLLOWERS SET DISPLAYNAME = :DISPLAYNAME, PICTUREURL = :PICTUREURL, " +
                   "STATUSMESSAGE = :STATUSMESSAGE, UDT = NOW() WHERE USERID = :USERID", nativeQuery = true)
    public void updateFollowerInfo(@Param("DISPLAYNAME") String DISPLAYNAME, @Param("PICTUREURL") String PICTUREURL, 
    		                       @Param("STATUSMESSAGE") String STATUSMESSAGE, @Param("USERID") String USERID);
    
    @Query(value = "SELECT * FROM LINE_FOLLOWERS WHERE USERID = :USERID", nativeQuery = true)
    public List<followersEntity> getSpecFollower(@Param("USERID") String USERID);
}
