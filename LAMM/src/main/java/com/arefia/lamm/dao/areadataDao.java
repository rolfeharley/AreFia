package com.arefia.lamm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.lamm.entity.areadataEntity;

public interface areadataDao extends JpaRepository<areadataEntity, Long> {
	@Query(value = "SELECT AREAID, CITYID, AREANAME FROM AREADATA WHERE CITYID = :CITYID ORDER BY AREANAME", nativeQuery = true)
	public List<areadataEntity> getAreaOfCityAsc(@Param("CITYID") String cityid);
	
	@Transactional
    @Modifying
    @Query(value = "INSERT INTO AREADATA (AREAID, CITYID, AREANAME) VALUES(:AREAID, (SELECT MAX(CITYID) FROM CITYSDATA WHERE CITYNAME = :CITYNAME), :AREANAME)", nativeQuery = true)
    public void insertareaemp(@Param("CITYNAME") String cityname, @Param("AREAID") String areaid, @Param("AREANAME") String areaname);
}