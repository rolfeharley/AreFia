package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.lamm.entity.flexdetailEntity;

public interface flexDetailDao extends JpaRepository<flexdetailEntity, Long> {
	@Query(value = "SELECT * FROM FLEX_DETAIL WHERE UID = :UID ORDER BY BLOCKNUM", nativeQuery = true)
    public List<flexdetailEntity> getSingleFlexDetail(@Param("UID") String uid);
}
