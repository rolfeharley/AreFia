package com.arefia.lamm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.arefia.lamm.entity.msgpushEntity;

public interface msgpushDao extends JpaRepository<msgpushEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE MSG_PUSH SET CHECKFLAG = 2 WHERE SOURCERID = :SOURCERID", nativeQuery = true)
    public void movePushToHistory(@Param("SOURCERID") String SOURCERID);
}
