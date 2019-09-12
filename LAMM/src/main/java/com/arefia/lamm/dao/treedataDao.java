package com.arefia.lamm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.lamm.entity.treedataEntity;

public interface treedataDao extends JpaRepository<treedataEntity, Long> {
	@Query(value = "SELECT TREEID, AREAID, TREENAME FROM TREEDATA WHERE AREAID = :AREAID ORDER BY TREENAME", nativeQuery = true)
	public List<treedataEntity> getTreeOfAreaAsc(@Param("AREAID") String cityid);
	
	@Transactional
    @Modifying
    @Query(value = "INSERT INTO TREEDATA (AREAID, TREEID, TREENAME) VALUES((SELECT MAX(A.AREAID) AREAID FROM AREADATA A JOIN CITYSDATA B ON B.CITYID = A.CITYID WHERE B.CITYNAME = :CITYNAME AND A.AREANAME = :AREANAME), :TREEID, :TREENAME)", nativeQuery = true)
    public void inserttreetemp(@Param("CITYNAME") String cityname, @Param("AREANAME") String areaname, @Param("TREEID") String treeid, @Param("TREENAME") String treename);
}
