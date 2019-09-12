package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arefia.lamm.entity.citysdataEntity;

public interface citysdataDao extends JpaRepository<citysdataEntity, Long> {
	@Query(value = "SELECT CITYID, CITYNAME FROM CITYSDATA ORDER BY CITYORDER", nativeQuery = true)
	public List<citysdataEntity> getAllCitysAsc();
}