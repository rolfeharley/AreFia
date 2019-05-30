package com.arefia.bcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.bcs.entity.menuEntity;

public interface menuDao extends JpaRepository<menuEntity, Long> {
	@Query(value = "SELECT A.APPID, A.APPICON, A.APPURL FROM APP_LIST A " +
                   "JOIN APP_AUTH B ON B.APPID = A.APPID " +
                   "JOIN GROUP_LIST C ON C.GROUPID = B.GROUPID " +
                   "WHERE A.ISINDEX = '0' AND A.ISSERVICE = '0' AND (C.GROUPID IN " +
                   "(SELECT GROUPID FROM USER_IN_GROUP WHERE ACCOUNTID = :ACCOUNTID) OR " +
                   "(SELECT COUNT(*) FROM USER_IN_GROUP WHERE ACCOUNTID = :ACCOUNTID AND GROUPID = 'sysadm') > 0) " +
                   "AND C.ISENABLE = '1' " +
                   "GROUP BY A.APPID, A.APPICON, A.APPURL", nativeQuery = true)
    public List<menuEntity> findMenuAuthed(@Param("ACCOUNTID") String accountid);
    
    @Query(value = "SELECT APPID FROM APP_LIST WHERE CONTROLNAME = :CONTROLNAME", nativeQuery = true)
    public String getAppID(@Param("CONTROLNAME") String controlname);
    
    @Query(value = "SELECT COUNT(*) AS ACCESSCNT FROM APP_LIST A " + 
    		       "JOIN APP_AUTH B ON B.APPID = A.APPID " + 
    		       "JOIN USER_IN_GROUP C ON C.GROUPID = B.GROUPID " + 
    		       "WHERE A.CONTROLNAME = :CONTROLNAME AND C.ACCOUNTID = :ACCOUNTID", nativeQuery = true)
    public String getAccessSatus(@Param("CONTROLNAME") String controlname, @Param("ACCOUNTID") String accountid);
}
