package com.arefia.bcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.arefia.bcs.entity.usageEntity;

public interface usageDao extends JpaRepository<usageEntity, Long> {
	@Query(value = "INSERT INTO APP_USAGE (UID, ACCOUNTID, APPID, IPADDRESS, VISITDATE) VALUES (:UID, :ACCOUNTID, :APPID, :IPADDRESS, NOW())", nativeQuery = true)
    @Modifying
    @Transactional
    public int insertUsage(@Param("UID") String UID, @Param("ACCOUNTID") String ACCOUNTID, @Param("APPID") String APPID, @Param("IPADDRESS") String ipaddress);
	
	@Query(value = "UPDATE APP_USAGE SET IPADDRESS = :IPADDRESS, VISITDATE = NOW() WHERE UID = :UID", nativeQuery = true)
    @Modifying
    @Transactional
    public int updatetUsage(@Param("IPADDRESS") String ipaddress, @Param("UID") String UID);
	
	@Query(value = "SELECT * FROM APP_USAGE WHERE ACCOUNTID = :ACCOUNTID", nativeQuery = true)
	public List<usageEntity> getUsageByUser(@Param("ACCOUNTID") String accountid);
	
	@Query(value = "SELECT * FROM APP_USAGE WHERE APPID = :APPID", nativeQuery = true)
	public List<usageEntity> getUsageByApp(@Param("APPID") String appid);
	
	@Query(value = "SELECT * FROM APP_USAGE WHERE VISITDATE BETWEEN :STDATE AND :EDDATE", nativeQuery = true)
	public List<usageEntity> getUsageByDate(@Param("STDATE") String stdate, @Param("EDDATE") String eddate);
	
	@Query(value = "SELECT IF(MINUTE(TIMEDIFF(NOW(), VISITDATE)) > 30, '1', UID) AS EXT FROM APP_USAGE WHERE APPID = :APPID AND ACCOUNTID = :ACCOUNTID AND VISITDATE = (SELECT MAX(VISITDATE) FROM APP_USAGE WHERE APPID = :APPID AND ACCOUNTID = :ACCOUNTID)", nativeQuery = true)
	public String isInsertAllow(@Param("APPID") String appid, @Param("ACCOUNTID") String accountid);
}
