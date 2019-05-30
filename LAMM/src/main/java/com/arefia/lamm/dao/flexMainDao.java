package com.arefia.lamm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arefia.lamm.entity.flexmainEntity;

public interface flexMainDao extends JpaRepository<flexmainEntity, Long> {
	@Query(value = "SELECT A.UID, A.TITLE, DATE_FORMAT(A.CREATEDATE, '%Y/%m/%d') AS CREATEDATE " + 
			       "FROM FLEX_MAIN A JOIN FLEX_DETAIL B ON B.UID = A.UID " + 
			       "LEFT JOIN FLEX_FOLLOWERS C ON C.UID = A.UID " + 
			       "WHERE A.TITLE LIKE %:KEYWORD% OR B.BLOCKCONTENT LIKE %:KEYWORD% OR C.FOLLOWERID LIKE %:KEYWORD% " + 
			       "GROUP BY A.UID, A.TITLE, A.CREATEDATE ORDER BY A.CREATEDATE, A.TITLE", nativeQuery = true)
    public List<Object[]> getFlexMessagesList(@Param("KEYWORD") String keyword);
    
    @Query(value = "SELECT * FROM FLEX_MAIN WHERE UID = :UID", nativeQuery = true)
    public List<flexmainEntity> getSingleFlexMaster(@Param("UID") String uid);
}
